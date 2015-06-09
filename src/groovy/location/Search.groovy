package location

import groovy.xml.MarkupBuilder
import groovyx.net.http.HTTPBuilder

class Search {
    String address
    String businessName
    String city
    String genderCode
    String languageCode
    String lastName
    String latitude
    String longitude
    int maximumDistance
    int maximumNumberOfRecordsToReturn
    String officeTimes
    boolean oigIndicatorAll
    String productCode
    String specialtyCode
    String stateCode
    String zipCode

    def toSoapRequestMessage(){
        def writer = new StringWriter()
        def xml = new MarkupBuilder(writer)

        xml.search() {
            this.address ? address(this.address) : ''
            this.businessName ? businessName(this.businessName) : ''
            this.city ? city(this.city) : ''
            this.genderCode ? genderCode(this.genderCode) : ''
            this.languageCode ? language(this.language) : ''
            this.lastName ? lastName(this.lastName) : ''
            this.latitude ? latitude(this.latitude) : ''
            this.longitude ? longitude(this.longitude) : ''
            this.maximumDistance ? maximumDistance(this.maximumDistance) : ''
            this.maximumNumberOfRecordsToReturn ? maximumNumberOfRecordsToReturn(this.maximumNumberOfRecordsToReturn) : ''
            this.officeTimes ? officeTimes(this.officeTimes) : ''
            this.oigIndicatorAll ? oigIndicatorAll(this.oigIndicatorAll) : ''
            this.productCode ? productCode(this.productCode) : ''
            this.specialtyCode ? specialtyCode(this.specialtyCode) : ''
            this.stateCode ? stateCode(this.stateCode) : ''
            this.zipCode ? zipCode(this.zipCode) : ''
        }

        def requestBeginning = '''<?xml version='1.0' encoding='UTF-8'?>
        <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:m="http://metroservices.providerquest.deltadental.com/" xmlns="">
          <soap:Header/>
          <soap:Body>
            <m:retrieveLocations>
            '''

        def requestEnding = '''
            </m:retrieveLocations>
          </soap:Body>
         </soap:Envelope>
                '''
        return requestBeginning + writer.toString() + requestEnding

    }
}

