package sdk.jassinaturas.communicators;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sdk.jassinaturas.clients.attributes.Authentication;
import sdk.jassinaturas.feign.BasicAuthRequestInterceptor;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class Communicator {
	
	public <T> T build(Class<T> clazz, Authentication authentication){
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		return Feign
		.builder()
		.decoder(new GsonDecoder(gson))
		.encoder(new GsonEncoder(gson))
		.requestInterceptor(new BasicAuthRequestInterceptor(authentication.getToken(), authentication.getSecret()))
		.target(clazz,
				"https://sandbox.moip.com.br/assinaturas/v1");
	}
}
