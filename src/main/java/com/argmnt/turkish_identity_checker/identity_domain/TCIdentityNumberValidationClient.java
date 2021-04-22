package com.argmnt.turkish_identity_checker.identity_domain;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class TCIdentityNumberValidationClient extends WebServiceGatewaySupport {



    public TCKimlikNoDogrulaResponse isTCNoValid(TCKimlikNoDogrula request) {

        TCKimlikNoDogrulaResponse response = (TCKimlikNoDogrulaResponse) getWebServiceTemplate()
                .marshalSendAndReceive("https://tckimlik.nvi.gov.tr/Service/KPSPublic.asmx", request,
                        new SoapActionCallback(
                                "http://tckimlik.nvi.gov.tr/WS/TCKimlikNoDogrula"));

        return response;
    }
}
