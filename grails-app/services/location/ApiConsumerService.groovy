package location

import grails.transaction.Transactional

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.Method

@Transactional
class ApiConsumerService {

        static def postRequest(String apiPath, String requestMessage) {
            try {
                  def ret = null
                  def http = new HTTPBuilder('http://localhost:8080/finder/')

                  // perform a POST text/xml request, expecting text/xml response
                  http.request(Method.POST, ContentType.TEXT) {
                    uri.path = apiPath
                    headers.'Accept' = 'text/xml'
                    headers.'Content-Type' = 'text/xml'
                    body:requestMessage
                    response.success = { resp, reader ->
                        ret = reader.getText()
                    }
                  }

                  return ret
                }
            catch (groovyx.net.http.HttpResponseException ex) {
                ex.printStackTrace()
                return null
            } catch (java.net.ConnectException ex) {
                ex.printStackTrace()
                return null
            }
        }

}
