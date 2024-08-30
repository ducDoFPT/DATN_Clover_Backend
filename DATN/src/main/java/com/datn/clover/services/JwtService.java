//package com.datn.clover.services.sellers;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JwtService {
//    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
//
//    public String generateToken(String userName) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, userName);
//    }
//
//    private String createToken(Map<String, Object> claims, String userName) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(userName)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60* 24 *30)) // cài đặt thời gian hiệu lực của jwt
//                .signWith(getSignKey(), SignatureAlgorithm.HS256) // set sign và thuật toán xử lý
//                .compact();
//    }
//// tạo signkey từ chuổi secret
//    private Key getSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//// lấy username tu subject cua token
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//// lấy thời gian hệu lực của token
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        try{
//           return  Jwts
//                    .parserBuilder()
//                    .setSigningKey(getSignKey())
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//        }catch (ClaimJwtException c){
//             c.fillInStackTrace();
//             throw c;
//        }
//
//    }
//
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//}
