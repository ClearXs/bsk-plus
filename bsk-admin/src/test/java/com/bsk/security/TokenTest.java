package com.bsk.security;

import com.auth0.jwt.interfaces.Claim;
import com.bsk.BaseTest;
import com.bsk.security.util.TokenUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class TokenTest extends BaseTest {

    @Autowired
    private TokenUtils tokenUtils;

    @Test
    public void test() {
        String token = tokenUtils.createToken("13", "13", false);

        System.out.println(token);
        Map<String, Claim> claimMap = tokenUtils.decodeToken(token);
        claimMap.forEach((s, claim) -> {
            System.out.println(s);
            System.out.println(claim);
        });
    }
}
