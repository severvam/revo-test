package com.revo;

import com.google.gson.Gson;
import com.revo.account.AccountDto;
import com.revo.core.OperationResult;
import com.revo.transfer.TransferDto;
import okhttp3.*;

import java.io.IOException;
import java.util.Properties;

public class AppApi {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private Properties props = new Properties();

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();

    public void init() throws IOException {
        props.load(AppApi.class.getResourceAsStream("/cucumber.properties"));
    }


    public AccountDto getAccount(String accountId) throws IOException {
        Request request = new Request.Builder()
                .url(props.getProperty("url") + "/account/" + accountId)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        return gson.fromJson(response.body().string(), AccountDto.class);
    }

    public OperationResult createAccount(AccountDto accountDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, gson.toJson(accountDto));
        Request request = new Request.Builder()
                .url(props.getProperty("url") + "/account")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return gson.fromJson(response.body().string(), OperationResult.class);
    }

    public OperationResult transfer(TransferDto transferDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, gson.toJson(transferDto));
        Request request = new Request.Builder()
                .url(props.getProperty("url") + "/transfer")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return gson.fromJson(response.body().string(), OperationResult.class);
    }

}
