package com.psuti.buildcalculator.service.impl;

import com.psuti.buildcalculator.dao.TokenRepository;
import com.psuti.buildcalculator.entities.Token;
import com.psuti.buildcalculator.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class JwtTokenService {
    private final JwtTokenUtil<Integer> jwtUserIdTokenUtil;
    private final TokenRepository tokenRepository;

    @Autowired
    public JwtTokenService(JwtTokenUtil<Integer> jwtUserIdTokenUtil,
                           TokenRepository tokenRepository) {
        this.jwtUserIdTokenUtil = jwtUserIdTokenUtil;
        this.tokenRepository = tokenRepository;
    }
    public Token createToken(Integer userId){

        String token = jwtUserIdTokenUtil.generateToken(userId);

        Token tokenEntity = new Token();
        tokenEntity.setUserId(userId);
        tokenEntity.setValue(token);
        tokenEntity.setKilled(false);

        return tokenRepository.save(tokenEntity);
    }

    public void kill(String tokenValue){
        kill(tokenByValue(tokenValue));
    }

    public void kill(Token token){
        token.setKilled(true);
        tokenRepository.save(token);
    }

    public boolean tokenIsKilled(String tokenValue){
        return tokenByValue(tokenValue).isKilled();
    }

    public boolean tokenExists(String tokenValue){
        AtomicBoolean exists = new AtomicBoolean(false);

        tokenRepository.findByValue(tokenValue).ifPresent(
                (t)-> exists.set(!t.isKilled())
        );
        return exists.get();
    }


    private Token tokenByValue(String val){
        return tokenRepository.findByValue(val).orElseThrow(()->{
            throw new EntityExistsException("token with value: '" + val + "' doesn't exists");
        });
    }

    public Optional<Token> findOptionalByUserId(Integer id){
        return tokenRepository.findTokenByUserId(id);
    }

    public Token findByUserId(Integer id){
        return tokenRepository.findTokenByUserId(id).orElseThrow(()->{
            throw new EntityExistsException("token by user id: " + id + " doesn't exists");
        });
    }

    public boolean validateToken(String token, Integer id){
        if(!jwtUserIdTokenUtil.validateToken(token,id)){
            if(new Date().before(jwtUserIdTokenUtil.getExpirationDateFromToken(token))){
                kill(token);
            }
            return false;
        }
        return true;
    }

    public Integer getIdFromToken(String token){
        return jwtUserIdTokenUtil.getSubjectFromToken(token);
    }

    public Token refreshToken(String tokenValue){
        Token token = tokenByValue(tokenValue);
        token.setValue(jwtUserIdTokenUtil.generateToken(token.getUserId()));
        return tokenRepository.save(token);
    }
}

