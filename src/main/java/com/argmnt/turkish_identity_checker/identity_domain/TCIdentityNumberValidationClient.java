package com.argmnt.turkish_identity_checker.identity_domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class TCIdentityNumberValidationClient extends WebServiceGatewaySupport {

    @Value("${app.soap-service-url}")
    private String soapServiceUrlL;

    @Value("${app.soap-action-url}")
    private String soapActionUrl;

    public TCKimlikNoDogrulaResponse isTCNoValid(TCKimlikNoDogrula request) {

        TCKimlikNoDogrulaResponse response = (TCKimlikNoDogrulaResponse) getWebServiceTemplate()
                .marshalSendAndReceive(soapServiceUrlL, request,
                        new SoapActionCallback(
                                soapActionUrl));

        return response;
    }
}
