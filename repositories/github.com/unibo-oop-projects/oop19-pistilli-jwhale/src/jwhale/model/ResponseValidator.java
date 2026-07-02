package jwhale.model;

import java.net.http.HttpResponse;
/**
 * It handle connector response, take validation policy via response status code
 * and validate a response.
 */
public class ResponseValidator {
    private static final int DEF_OK = 200;
    private int okStatusCode = DEF_OK;
    /**
     * Set expected status code.
     * @param okStatusCode
     *          expected status code.
     */
    public final void setStatusCode(final int okStatusCode) {
        this.okStatusCode = okStatusCode;
    }
    /**
     * Validate a response.
     * @param response
     *          response to validate.
     * @return
     *          validation result.
     */
    public final boolean validate(final HttpResponse<String> response) {
        return response.statusCode() == okStatusCode;
    }

}
