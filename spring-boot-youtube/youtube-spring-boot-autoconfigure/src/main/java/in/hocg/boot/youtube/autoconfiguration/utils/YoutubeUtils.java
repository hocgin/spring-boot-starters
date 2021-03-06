package in.hocg.boot.youtube.autoconfiguration.utils;

import cn.hutool.crypto.SecureUtil;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import in.hocg.boot.web.autoconfiguration.SpringContext;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.List;

/**
 * Created by hocgin on 2021/6/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class YoutubeUtils {
    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    public static final JsonFactory JSON_FACTORY = new JacksonFactory();

    /**
     * 发起授权
     *
     * @param clientId
     * @param clientSecret
     * @param redirectUri
     * @return
     */
    public static String authorize(String clientId, String clientSecret, String redirectUri, List<String> scopes) {
        GoogleAuthorizationCodeFlow flow = getAuthorizationCodeFlow(clientId, clientSecret, scopes);
        return flow.newAuthorizationUrl().setRedirectUri(redirectUri).build();
    }

    /**
     * 获取凭证
     *
     * @param clientId
     * @param clientSecret
     * @param redirectUri
     * @param code
     * @return
     * @throws IOException
     */
    @SneakyThrows(IOException.class)
    public static Credential getCredential(String clientId, String clientSecret, String redirectUri, List<String> scopes, String code) {
        GoogleAuthorizationCodeFlow flow = getAuthorizationCodeFlow(clientId, clientSecret, scopes);
        TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectUri).execute();
        return flow.createAndStoreCredential(response, getUserId(clientId));
    }

    @SneakyThrows
    public static Credential loadCredential(String clientId, String clientSecret, List<String> scopes) {
        return getAuthorizationCodeFlow(clientId, clientSecret, scopes).loadCredential(YoutubeUtils.getUserId(clientId));
    }

    public static String getUserId(String clientId) {
        return SecureUtil.md5(clientId);
    }

    @SneakyThrows
    public static GoogleAuthorizationCodeFlow getAuthorizationCodeFlow(String clientId, String clientSecret, List<String> scopes) {
        return new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientId, clientSecret, scopes)
            .setCredentialDataStore(SpringContext.getBean(DataStoreFactory.class).getDataStore("boot_ytb_datastore"))
            .build();
    }

}
