package location

import grails.converters.JSON
import groovyx.net.http.Method

import groovy.xml.*

class LocationController {


        def index() { }

        def postRequest(){

            def search = new Search()
            search.stateCode = 'MN'
            search.zipCode = '55438'

            String xmlResponse = ApiConsumerService.postRequest('Location/soapSearchResults',search.toSoapRequestMessage())

            def webServiceResult = new XmlSlurper(false,true,true).parseText(xmlResponse).Body.retrieveLocationsResponse.WebServiceResult

            println 'webServiceResult: ' + webServiceResult.name() + ' ' + webServiceResult

            def searchResults = new SearchResults()
            searchResults.loadFromXML(webServiceResult)


            println searchResults.geoCodeQuality + ' ' + searchResults.alterRule + ' ' + searchResults.originatingLatitude + ' ' + searchResults.originatingLongitude + ' '

            render searchResults as JSON
            //render (text: xmlResponse, contentType:'text/xml')
        }


        def proximityWebServiceXSD(){

            def xsd =
                    '''<?xml version='1.0' encoding='UTF-8'?>
        <!-- Published by JAX-WS RI at http://jax-ws.dev.java.net. RI's version is Metro/2.2 (branches/2.2-7015; 2012-02-20T20:31:25+0000) JAXWS-RI/2.2.6 JAXWS/2.2 svn-revision#unknown. -->
        <xs:schema xmlns:tns="http://metroservices.providerquest.deltadental.com/" xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0" targetNamespace="http://metroservices.providerquest.deltadental.com/">

        <xs:element name="ProximityException" type="tns:ProximityException"/>

        <xs:element name="webServiceSearch" type="tns:webServiceSearch"/>

        <xs:complexType name="languages">
        <xs:sequence>
        <xs:element name="listOfLanguages" type="tns:language" nillable="true" minOccurs="0" maxOccurs="unbounded"/>
        </xs:sequence>
</xs:complexType>

        <xs:complexType name="language">
        <xs:sequence>
        <xs:element name="languageCode" type="xs:string" minOccurs="0"/>
        <xs:element name="languageDescription" type="xs:string" minOccurs="0"/>
        </xs:sequence>
</xs:complexType>

        <xs:complexType name="ProximityException">
        <xs:sequence>
        <xs:element name="message" type="xs:string" minOccurs="0"/>
        </xs:sequence>
</xs:complexType>

        <xs:complexType name="specialties">
        <xs:sequence>
        <xs:element name="listOfSpecialties" type="tns:specialty" nillable="true" minOccurs="0" maxOccurs="unbounded"/>
        </xs:sequence>
</xs:complexType>

        <xs:complexType name="specialty">
        <xs:sequence>
        <xs:element name="specialtyCode" type="xs:string" minOccurs="0"/>
        <xs:element name="specialtyDescription" type="xs:string" minOccurs="0"/>
        </xs:sequence>
</xs:complexType>

        <xs:complexType name="geoCodeAddress">
        <xs:sequence>
        <xs:element name="address" type="xs:string" minOccurs="0"/>
        <xs:element name="city" type="xs:string" minOccurs="0"/>
        <xs:element name="geoCodingQuality" type="xs:string" minOccurs="0"/>
        <xs:element name="latitude" type="xs:double"/>
        <xs:element name="longitude" type="xs:double"/>
        <xs:element name="stateCode" type="xs:string" minOccurs="0"/>
        <xs:element name="status" type="xs:string" minOccurs="0"/>
        <xs:element name="XAxis" type="xs:double"/>
        <xs:element name="YAxis" type="xs:double"/>
        <xs:element name="ZAxis" type="xs:double"/>
        <xs:element name="zip" type="xs:string" minOccurs="0"/>
        </xs:sequence>
</xs:complexType>

        <xs:complexType name="directionsServiceResult">
        <xs:sequence>
        <xs:element name="copyrights" type="xs:string" minOccurs="0"/>
        <xs:element name="route" type="tns:maneuver" nillable="true" minOccurs="0" maxOccurs="unbounded"/>
        <xs:element name="warnings" type="xs:string" nillable="true" minOccurs="0" maxOccurs="unbounded"/>
        </xs:sequence>
</xs:complexType>

        <xs:complexType name="maneuver">
        <xs:sequence>
        <xs:element name="direction" type="xs:string" minOccurs="0"/>
        <xs:element name="distance" type="xs:string" minOccurs="0"/>
        <xs:element name="name" type="xs:string" minOccurs="0"/>
        <xs:element name="step" type="xs:int"/>
        </xs:sequence>
</xs:complexType>

        <xs:complexType name="webServiceSearch">
        <xs:sequence>
        <xs:element name="address" type="xs:string" minOccurs="0"/>
        <xs:element name="businessName" type="xs:string" minOccurs="0"/>
        <xs:element name="city" type="xs:string" minOccurs="0"/>
        <xs:element name="genderCode" type="xs:string" minOccurs="0"/>
        <xs:element name="languageCode" type="xs:string" minOccurs="0"/>
        <xs:element name="lastName" type="xs:string" minOccurs="0"/>
        <xs:element name="latitude" type="xs:string" minOccurs="0"/>
        <xs:element name="longitude" type="xs:string" minOccurs="0"/>
        <xs:element name="maximumDistance" type="xs:int"/>
        <xs:element name="maximumNumberOfRecordsToReturn" type="xs:int"/>
        <xs:element name="officeTimes" type="xs:string" minOccurs="0"/>
        <xs:element name="oigIndicatorAll" type="xs:boolean"/>
        <xs:element name="productCode" type="xs:string" minOccurs="0"/>
        <xs:element name="specialtyCode" type="xs:string" minOccurs="0"/>
        <xs:element name="stateCode" type="xs:string" minOccurs="0"/>
        <xs:element name="zipCode" type="xs:string" minOccurs="0"/>
        </xs:sequence>
</xs:complexType>

        <xs:complexType name="webServiceResult">
        <xs:sequence>
        <xs:element name="alterRule" type="xs:string" minOccurs="0"/>
        <xs:element name="geoCodeQuality" type="xs:string" minOccurs="0"/>
        <xs:element name="listOfLocations" type="tns:webServiceLocation" nillable="true" minOccurs="0" maxOccurs="unbounded"/>
        <xs:element name="originatingLatitude" type="xs:double"/>
        <xs:element name="originatingLongitude" type="xs:double"/>
        <xs:element name="status" type="xs:string" minOccurs="0"/>
        </xs:sequence>
</xs:complexType>

        <xs:complexType name="webServiceLocation">
        <xs:sequence>
        <xs:element name="addressOne" type="xs:string" minOccurs="0"/>
        <xs:element name="addressTwo" type="xs:string" minOccurs="0"/>
        <xs:element name="businessName" type="xs:string" minOccurs="0"/>
        <xs:element name="city" type="xs:string" minOccurs="0"/>
        <xs:element name="distance" type="xs:double"/>
        <xs:element name="education" type="tns:education" nillable="true" minOccurs="0" maxOccurs="unbounded"/>
        <xs:element name="facilityID" type="xs:string" minOccurs="0"/>
        <xs:element name="firstName" type="xs:string" minOccurs="0"/>
        <xs:element name="genderCode" type="xs:string" minOccurs="0"/>
        <xs:element name="handicappedAccessible" type="xs:boolean"/>
        <xs:element name="lastName" type="xs:string" minOccurs="0"/>
        <xs:element name="latitude" type="xs:double"/>
        <xs:element name="locationSid" type="xs:long"/>
        <xs:element name="longitude" type="xs:double"/>
        <xs:element name="middleInitial" type="xs:string" minOccurs="0"/>
        <xs:element name="officeEmail" type="xs:string" minOccurs="0"/>
        <xs:element name="officeEmergencyMessage" type="xs:string" minOccurs="0"/>
        <xs:element name="officeLanguages" type="xs:string" nillable="true" minOccurs="0" maxOccurs="unbounded"/>
        <xs:element name="officeServcDisableAdlt" type="xs:boolean"/>
        <xs:element name="officeServcDisableChld" type="xs:boolean"/>
        <xs:element name="officeTimes" type="xs:string" nillable="true" minOccurs="0" maxOccurs="unbounded"/>
        <xs:element name="products" type="tns:product" nillable="true" minOccurs="0" maxOccurs="unbounded"/>
        <xs:element name="providerEmail" type="xs:string" minOccurs="0"/>
        <xs:element name="providerServcDisableAdlt" type="xs:boolean"/>
        <xs:element name="providerServcDisableChld" type="xs:boolean"/>
        <xs:element name="prvdrLanguages" type="xs:string" nillable="true" minOccurs="0" maxOccurs="unbounded"/>
        <xs:element name="publicTransAvail" type="xs:boolean"/>
        <xs:element name="specialties" type="xs:string" nillable="true" minOccurs="0" maxOccurs="unbounded"/>
        <xs:element name="stateCode" type="xs:string" minOccurs="0"/>
        <xs:element name="suffix" type="xs:string" minOccurs="0"/>
        <xs:element name="telephoneNumber" type="xs:string" minOccurs="0"/>
        <xs:element name="urlText" type="xs:string" minOccurs="0"/>
        <xs:element name="zip" type="xs:string" minOccurs="0"/>
        </xs:sequence>
</xs:complexType>

        <xs:complexType name="education">
        <xs:sequence>
        <xs:element name="educationDesc" type="xs:string" minOccurs="0"/>
        <xs:element name="institutionName" type="xs:string" minOccurs="0"/>
        </xs:sequence>
</xs:complexType>

        <xs:complexType name="product">
        <xs:sequence>
        <xs:element name="productCode" type="xs:string" minOccurs="0"/>
        <xs:element name="productDescription" type="xs:string" minOccurs="0"/>
        <xs:element name="prvdrAccptsPatsInd" type="xs:string" minOccurs="0"/>
        <xs:element name="soAccptsPatsInd" type="xs:string" minOccurs="0"/>
        </xs:sequence>
</xs:complexType>
        </xs:schema>'''

            render(text: xsd, contentType: "text/xml", encoding: "UTF-8")

        }

        def proximityWebServiceWSDL(){
            def wsdl =
                    '''<?xml version='1.0' encoding='UTF-8'?>
<!-- Published by JAX-WS RI at http://jax-ws.dev.java.net. RI's version is Metro/2.2 (branches/2.2-7015; 2012-02-20T20:31:25+0000) JAXWS-RI/2.2.6 JAXWS/2.2 svn-revision#unknown. -->
<!-- Generated by JAX-WS RI at http://jax-ws.dev.java.net. RI's version is Metro/2.2 (branches/2.2-7015; 2012-02-20T20:31:25+0000) JAXWS-RI/2.2.6 JAXWS/2.2 svn-revision#unknown. -->
<definitions xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" xmlns:wsp="http://www.w3.org/ns/ws-policy" xmlns:wsp1_2="http://schemas.xmlsoap.org/ws/2004/09/policy" xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://metroservices.providerquest.deltadental.com/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://schemas.xmlsoap.org/wsdl/" targetNamespace="http://metroservices.providerquest.deltadental.com/" name="ProximityWebServiceService">
<types>
<xsd:schema>
<xsd:import namespace="http://metroservices.providerquest.deltadental.com/" schemaLocation="http://localhost:8080/findadentist/Dentist/proximityWebServiceXSD"/>
<!--schemaLocation="http://172.29.166.87:8100/fj/proximitywebservice?xsd=1"/-->
</xsd:schema>
</types>
<message name="retrieveLanguages"/>
<message name="retrieveLanguagesResponse">
<part name="Languages" type="tns:languages"/>
</message>
<message name="ProximityException">
<part name="fault" element="tns:ProximityException"/>
</message>
<message name="retrieveSpecialties"/>
<message name="retrieveSpecialtiesResponse">
<part name="Specialties" type="tns:specialties"/>
</message>
<message name="retrieveLocations">
<part name="search" type="tns:webServiceSearch"/>
</message>
<message name="retrieveLocationsResponse">
<part name="WebServiceResult" type="tns:webServiceResult"/>
</message>
<message name="retrieveDirections">
<part name="starting" type="tns:geoCodeAddress"/>
<part name="ending" type="tns:geoCodeAddress"/>
</message>
<message name="retrieveDirectionsResponse">
<part name="DirectionsResult" type="tns:directionsServiceResult"/>
</message>
<portType name="ProximityWebService">
<operation name="retrieveLanguages">
<input wsam:Action="urn:retrieveLanguages" message="tns:retrieveLanguages"/>
<output wsam:Action="http://metroservices.providerquest.deltadental.com/ProximityWebService/retrieveLanguagesResponse" message="tns:retrieveLanguagesResponse"/>
<fault message="tns:ProximityException" name="ProximityException" wsam:Action="http://metroservices.providerquest.deltadental.com/ProximityWebService/retrieveLanguages/Fault/ProximityException"/>
</operation>
<operation name="retrieveSpecialties">
<input wsam:Action="urn:retrieveSpecialties" message="tns:retrieveSpecialties"/>
<output wsam:Action="http://metroservices.providerquest.deltadental.com/ProximityWebService/retrieveSpecialtiesResponse" message="tns:retrieveSpecialtiesResponse"/>
<fault message="tns:ProximityException" name="ProximityException" wsam:Action="http://metroservices.providerquest.deltadental.com/ProximityWebService/retrieveSpecialties/Fault/ProximityException"/>
</operation>
<operation name="retrieveLocations">
<input wsam:Action="urn:retrieveLocations" message="tns:retrieveLocations"/>
<output wsam:Action="http://localhost:8080/findadentist/Dentist/proximityWebServiceResponse" message="tns:retrieveLocationsResponse"/>
<fault message="tns:ProximityException" name="ProximityException" wsam:Action="http://localhost:8080/findadentist/Dentist/proximityWebServiceResponse"/>
</operation>
<operation name="retrieveDirections" parameterOrder="starting ending">
<input wsam:Action="urn:retrieveDirections" message="tns:retrieveDirections"/>
<output wsam:Action="http://metroservices.providerquest.deltadental.com/ProximityWebService/retrieveDirectionsResponse" message="tns:retrieveDirectionsResponse"/>
<fault message="tns:ProximityException" name="ProximityException" wsam:Action="http://metroservices.providerquest.deltadental.com/ProximityWebService/retrieveDirections/Fault/ProximityException"/>
</operation>
</portType>
<binding name="ProximityWebServicePortBinding" type="tns:ProximityWebService">
<soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="rpc"/>
<operation name="retrieveLanguages">
<soap:operation soapAction="urn:retrieveLanguages"/>
<input>
<soap:body use="literal" namespace="http://metroservices.providerquest.deltadental.com/"/>
</input>
<output>
<soap:body use="literal" namespace="http://metroservices.providerquest.deltadental.com/"/>
</output>
<fault name="ProximityException">
<soap:fault name="ProximityException" use="literal"/>
</fault>
</operation>
<operation name="retrieveSpecialties">
<soap:operation soapAction="urn:retrieveSpecialties"/>
<input>
<soap:body use="literal" namespace="http://metroservices.providerquest.deltadental.com/"/>
</input>
<output>
<soap:body use="literal" namespace="http://metroservices.providerquest.deltadental.com/"/>
</output>
<fault name="ProximityException">
<soap:fault name="ProximityException" use="literal"/>
</fault>
</operation>
<operation name="retrieveLocations">
<soap:operation soapAction="urn:retrieveLocations"/>
<input>
<soap:body use="literal" namespace="http://metroservices.providerquest.deltadental.com/"/>
</input>
<output>
<soap:body use="literal" namespace="http://metroservices.providerquest.deltadental.com/"/>
</output>
<fault name="ProximityException">
<soap:fault name="ProximityException" use="literal"/>
</fault>
</operation>
<operation name="retrieveDirections">
<soap:operation soapAction="urn:retrieveDirections"/>
<input>
<soap:body use="literal" namespace="http://metroservices.providerquest.deltadental.com/"/>
</input>
<output>
<soap:body use="literal" namespace="http://metroservices.providerquest.deltadental.com/"/>
</output>
<fault name="ProximityException">
<soap:fault name="ProximityException" use="literal"/>
</fault>
</operation>
</binding>
<service name="ProximityWebServiceService">
<port name="ProximityWebServicePort" binding="tns:ProximityWebServicePortBinding">
<!--soap:address location="http://172.29.166.87:8100/fj/proximitywebservice"/-->
<soap:address location="http://localhost:8080/findadentist/Dentist/proximityWebServiceResponse"/><!--location="http://ddmn-rhel-01.dev.deltamn.us:8080/findadentist/Dentist/proximityWebServiceResponse"/-->
</port>
</service>
</definitions>'''

            render(text: wsdl, contentType: "text/xml", encoding: "UTF-8")

        }

        def soapSearchResults(){
            def responseXML = '''<?xml version='1.0' encoding='UTF-8'?>
         <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
          <S:Body>
           <ns2:retrieveLocationsResponse xmlns:ns2="http://metroservices.providerquest.deltadental.com/">
<WebServiceResult>
   <alterRule>NoFilter</alterRule>
   <geoCodeQuality>APPROXIMATE</geoCodeQuality>
   <listOfLocations>
      <addressOne>8910 PENN AVE S</addressOne>
      <businessName>JORGENSON SMILE CARE CENTER</businessName>
      <city>BLOOMINGTON</city>
      <distance>0.0</distance>
      <facilityID>0</facilityID>
      <firstName>MICHAEL</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>false</handicappedAccessible>
      <lastName>JORGENSON</lastName>
      <latitude>44.82422870000001</latitude>
      <locationSid>275646184</locationSid>
      <longitude>-93.3741647</longitude>
      <middleInitial>W</middleInitial>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <products>
         <productCode>20</productCode>
         <productDescription>Delta Dental PPO</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>Prosthodontist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>6122298471</telephoneNumber>
      <zip>554380000</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>5320 Hyland Greens Dr Ste 200</addressOne>
      <businessName>Park Dental Bloomington</businessName>
      <city>Bloomington</city>
      <distance>1.0737468400928751</distance>
      <facilityID>06</facilityID>
      <firstName>Kevin</firstName>
      <handicappedAccessible>false</handicappedAccessible>
      <lastName>Lahr</lastName>
      <latitude>44.824668</latitude>
      <locationSid>275976728</locationSid>
      <longitude>-93.352251</longitude>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>30</productCode>
         <productDescription>DeltaCare USA</productDescription>
         <soAccptsPatsInd>Y</soAccptsPatsInd>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528316126</telephoneNumber>
      <zip>55437</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>5320 Hyland Greens Dr Ste 200</addressOne>
      <businessName>Park Dental Bloomington</businessName>
      <city>Bloomington</city>
      <distance>1.0737468400928751</distance>
      <facilityID>06</facilityID>
      <firstName>Tara</firstName>
      <handicappedAccessible>false</handicappedAccessible>
      <lastName>Brodersen</lastName>
      <latitude>44.824668</latitude>
      <locationSid>275966791</locationSid>
      <longitude>-93.352251</longitude>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>30</productCode>
         <productDescription>DeltaCare USA</productDescription>
         <soAccptsPatsInd>Y</soAccptsPatsInd>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528316126</telephoneNumber>
      <zip>55437</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>5320 Hyland Greens Dr Ste 200</addressOne>
      <businessName>Park Dental Bloomington</businessName>
      <city>Bloomington</city>
      <distance>1.0737468400928751</distance>
      <facilityID>06</facilityID>
      <firstName>Michael</firstName>
      <handicappedAccessible>false</handicappedAccessible>
      <lastName>Routt</lastName>
      <latitude>44.824668</latitude>
      <locationSid>275963107</locationSid>
      <longitude>-93.352251</longitude>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>30</productCode>
         <productDescription>DeltaCare USA</productDescription>
         <soAccptsPatsInd>Y</soAccptsPatsInd>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528316126</telephoneNumber>
      <zip>55437</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>5300 HYLAND GREENS DR</addressOne>
      <addressTwo>STE 100</addressTwo>
      <businessName>NORMANDALE DENTAL PA</businessName>
      <city>BLOOMINGTON</city>
      <distance>1.1229534616246808</distance>
      <facilityID>0</facilityID>
      <firstName>RENEE</firstName>
      <genderCode>F</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>LEHN</lastName>
      <latitude>44.8243389</latitude>
      <locationSid>275744853</locationSid>
      <longitude>-93.3512382</longitude>
      <middleInitial>J</middleInitial>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528889661</telephoneNumber>
      <zip>554370000</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>10851 RHODE ISLAND AVE S</addressOne>
      <businessName>RIVER BLUFF DENTAL PA</businessName>
      <city>BLOOMINGTON</city>
      <distance>1.2261455190621415</distance>
      <facilityID>0</facilityID>
      <firstName>WALTER</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>PALMER</lastName>
      <latitude>44.807275</latitude>
      <locationSid>275758486</locationSid>
      <longitude>-93.3816029</longitude>
      <middleInitial>J</middleInitial>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>20</productCode>
         <productDescription>Delta Dental PPO</productDescription>
      </products>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528845361</telephoneNumber>
      <zip>554382393</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>10851 RHODE ISLAND AVE S</addressOne>
      <businessName>RIVER BLUFF DENTAL PA</businessName>
      <city>BLOOMINGTON</city>
      <distance>1.2261455190621415</distance>
      <facilityID>0</facilityID>
      <firstName>KENNETH</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>KOKKINEN</lastName>
      <latitude>44.807275</latitude>
      <locationSid>275879226</locationSid>
      <longitude>-93.3816029</longitude>
      <middleInitial>A</middleInitial>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <products>
         <productCode>20</productCode>
         <productDescription>Delta Dental PPO</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528845361</telephoneNumber>
      <zip>554382393</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>5129 W 98TH ST</addressOne>
      <businessName>NORMANDALE VILLAGE DENTISTRY</businessName>
      <city>BLOMINGTON</city>
      <distance>1.3565578301354118</distance>
      <facilityID>0</facilityID>
      <firstName>NANCY</firstName>
      <genderCode>F</genderCode>
      <handicappedAccessible>false</handicappedAccessible>
      <lastName>ZEIS</lastName>
      <latitude>44.8250885</latitude>
      <locationSid>275706739</locationSid>
      <longitude>-93.3464946</longitude>
      <middleInitial>L</middleInitial>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528355082</telephoneNumber>
      <zip>554370000</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>9613 ANDERSON LAKES PKWY</addressOne>
      <businessName>PARKWAY DENTAL</businessName>
      <city>EDEN PRAIRIE</city>
      <distance>1.5634488100901187</distance>
      <facilityID>0</facilityID>
      <firstName>DENNIS</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>KAIVO</lastName>
      <latitude>44.8368561</latitude>
      <locationSid>275718523</locationSid>
      <longitude>-93.4006621</longitude>
      <middleInitial>L</middleInitial>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9529410470</telephoneNumber>
      <zip>553444155</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>9613 ANDERSON LAKES PKWY</addressOne>
      <businessName>PARKWAY DENTAL</businessName>
      <city>EDEN PRAIRIE</city>
      <distance>1.5634488100901187</distance>
      <facilityID>0</facilityID>
      <firstName>JENNIFER</firstName>
      <genderCode>F</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>HUMMEL</lastName>
      <latitude>44.8368561</latitude>
      <locationSid>275894235</locationSid>
      <longitude>-93.4006621</longitude>
      <middleInitial>A</middleInitial>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9529410470</telephoneNumber>
      <zip>553444155</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>4200 W OLD SHAKOPEE RD</addressOne>
      <addressTwo>STE 221</addressTwo>
      <businessName>KRIS J KALENDA DDS</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.1905302012934524</distance>
      <facilityID>0</facilityID>
      <firstName>KRIS</firstName>
      <genderCode>F</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>KALENDA</lastName>
      <latitude>44.8103176</latitude>
      <locationSid>275660619</locationSid>
      <longitude>-93.3339762</longitude>
      <middleInitial>J</middleInitial>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <products>
         <productCode>20</productCode>
         <productDescription>Delta Dental PPO</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>true</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528810201</telephoneNumber>
      <zip>554372967</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>4200 W OLD SHAKOPEE RD</addressOne>
      <addressTwo>STE 100</addressTwo>
      <businessName>PARK DENTAL BLOOMINGTON</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.1905302012934524</distance>
      <facilityID>0</facilityID>
      <firstName>MICHAEL</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>ROUTT</lastName>
      <latitude>44.8103176</latitude>
      <locationSid>275791000</locationSid>
      <longitude>-93.3339762</longitude>
      <middleInitial>J</middleInitial>
      <officeEmail>EALM@PARKDENTAL.COM</officeEmail>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <products>
         <productCode>20</productCode>
         <productDescription>Delta Dental PPO</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>true</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528316126</telephoneNumber>
      <zip>554370000</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>4200 W OLD SHAKOPEE RD</addressOne>
      <addressTwo>STE 100</addressTwo>
      <businessName>PARK DENTAL BLOOMINGTON</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.1905302012934524</distance>
      <facilityID>0</facilityID>
      <firstName>TARA</firstName>
      <genderCode>F</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>BRODERSEN</lastName>
      <latitude>44.8103176</latitude>
      <locationSid>275810272</locationSid>
      <longitude>-93.3339762</longitude>
      <middleInitial>V</middleInitial>
      <officeEmail>EALM@PARKDENTAL.COM</officeEmail>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>20</productCode>
         <productDescription>Delta Dental PPO</productDescription>
      </products>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>true</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528316126</telephoneNumber>
      <zip>554370000</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>4200 W OLD SHAKOPEE RD</addressOne>
      <addressTwo>STE 100</addressTwo>
      <businessName>PARK DENTAL BLOOMINGTON</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.1905302012934524</distance>
      <facilityID>0</facilityID>
      <firstName>KEVIN</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>LAHR</lastName>
      <latitude>44.8103176</latitude>
      <locationSid>275806364</locationSid>
      <longitude>-93.3339762</longitude>
      <middleInitial>P</middleInitial>
      <officeEmail>EALM@PARKDENTAL.COM</officeEmail>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>20</productCode>
         <productDescription>Delta Dental PPO</productDescription>
      </products>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>true</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528316126</telephoneNumber>
      <zip>554370000</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>5270 W 84TH ST</addressOne>
      <addressTwo>STE 360</addressTwo>
      <businessName>IN HARMONY DENTAL CARE</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.317820451583335</distance>
      <facilityID>0</facilityID>
      <firstName>XUAN</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>false</handicappedAccessible>
      <lastName>THOU</lastName>
      <latitude>44.8522056</latitude>
      <locationSid>275748168</locationSid>
      <longitude>-93.34801399999999</longitude>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <prvdrLanguages>Vietnamese</prvdrLanguages>
      <publicTransAvail>false</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528350666</telephoneNumber>
      <zip>554370000</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>5270 W 84TH ST STE 360</addressOne>
      <businessName>GREGORY B HANSON DDS</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.317820451583335</distance>
      <facilityID>0</facilityID>
      <firstName>GREGORY</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>HANSON</lastName>
      <latitude>44.8522056</latitude>
      <locationSid>275834072</locationSid>
      <longitude>-93.34801399999999</longitude>
      <middleInitial>B</middleInitial>
      <officeLanguages>Portuguese</officeLanguages>
      <officeLanguages>Spanish</officeLanguages>
      <officeLanguages>Russian</officeLanguages>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>true</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528350666</telephoneNumber>
      <zip>554371377</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>5270 W 84TH ST</addressOne>
      <addressTwo>STE 360</addressTwo>
      <businessName>IN HARMONY DENTAL CARE</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.317820451583335</distance>
      <facilityID>0</facilityID>
      <firstName>GREGORY</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>false</handicappedAccessible>
      <lastName>HANSON</lastName>
      <latitude>44.8522056</latitude>
      <locationSid>275895252</locationSid>
      <longitude>-93.34801399999999</longitude>
      <middleInitial>B</middleInitial>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528350666</telephoneNumber>
      <zip>554370000</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>10617 FRANCE AVE</addressOne>
      <businessName>TC ORTHODONTICS</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.3574866169828774</distance>
      <facilityID>0</facilityID>
      <firstName>CHAD</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>false</handicappedAccessible>
      <lastName>KELLY</lastName>
      <latitude>44.8116165</latitude>
      <locationSid>275700715</locationSid>
      <longitude>-93.3294426</longitude>
      <middleInitial>H</middleInitial>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>20</productCode>
         <productDescription>Delta Dental PPO</productDescription>
      </products>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>Orthodontist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528849161</telephoneNumber>
      <zip>554310000</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>10617 FRANCE AVE</addressOne>
      <businessName>KUIPERS ORTHODONTICS</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.3574866169828774</distance>
      <facilityID>0</facilityID>
      <firstName>PETER</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>KUIPERS</lastName>
      <latitude>44.8116165</latitude>
      <locationSid>275763647</locationSid>
      <longitude>-93.3294426</longitude>
      <middleInitial>W</middleInitial>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>20</productCode>
         <productDescription>Delta Dental PPO</productDescription>
      </products>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>true</publicTransAvail>
      <specialties>Orthodontist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528849161</telephoneNumber>
      <zip>554310000</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>10611 FRANCE AVE S</addressOne>
      <addressTwo>STE 200</addressTwo>
      <businessName>METRO DENTALCARE BLOOMINGTON</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.3747472468009176</distance>
      <facilityID>0</facilityID>
      <firstName>NATALIE</firstName>
      <genderCode>F</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>SYRBU</lastName>
      <latitude>44.81163309999999</latitude>
      <locationSid>275641810</locationSid>
      <longitude>-93.32905439999999</longitude>
      <officeEmail>TYANBROCKLIN@AMDPI.COM</officeEmail>
      <officeLanguages>Persian</officeLanguages>
      <officeLanguages>Arabic</officeLanguages>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>20</productCode>
         <productDescription>Delta Dental PPO</productDescription>
      </products>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>true</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528884125</telephoneNumber>
      <zip>554313538</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>10611 FRANCE AVE S</addressOne>
      <addressTwo>STE 200</addressTwo>
      <businessName>METRO DENTALCARE BLOOMINGTON</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.3747472468009176</distance>
      <facilityID>78</facilityID>
      <firstName>SATBIR</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>BRAR</lastName>
      <latitude>44.81163309999999</latitude>
      <locationSid>275681713</locationSid>
      <longitude>-93.32905439999999</longitude>
      <middleInitial>S</middleInitial>
      <officeEmail>TYANBROCKLIN@AMDPI.COM</officeEmail>
      <officeLanguages>Persian</officeLanguages>
      <officeLanguages>Arabic</officeLanguages>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <products>
         <productCode>20</productCode>
         <productDescription>Delta Dental PPO</productDescription>
      </products>
      <products>
         <productCode>30</productCode>
         <productDescription>DeltaCare USA</productDescription>
         <soAccptsPatsInd>Y</soAccptsPatsInd>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>true</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528884125</telephoneNumber>
      <zip>554313538</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>10611 FRANCE AVE S</addressOne>
      <addressTwo>STE 201</addressTwo>
      <businessName>POINT FAMILY DENTISTRY PLLC</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.3747472468009176</distance>
      <facilityID>0</facilityID>
      <firstName>EDMUND</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>THEIS</lastName>
      <latitude>44.81163309999999</latitude>
      <locationSid>275698394</locationSid>
      <longitude>-93.32905439999999</longitude>
      <middleInitial>C</middleInitial>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528818404</telephoneNumber>
      <zip>554310000</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>10611 FRANCE AVE S</addressOne>
      <addressTwo>STE 200</addressTwo>
      <businessName>METRO DENTALCARE BLOOMINGTON</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.3747472468009176</distance>
      <facilityID>78</facilityID>
      <firstName>BRIAN</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>VIEREGGE</lastName>
      <latitude>44.81163309999999</latitude>
      <locationSid>275815146</locationSid>
      <longitude>-93.32905439999999</longitude>
      <middleInitial>L</middleInitial>
      <officeEmail>TYANBROCKLIN@AMDPI.COM</officeEmail>
      <officeLanguages>Persian</officeLanguages>
      <officeLanguages>Arabic</officeLanguages>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>20</productCode>
         <productDescription>Delta Dental PPO</productDescription>
      </products>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <products>
         <productCode>30</productCode>
         <productDescription>DeltaCare USA</productDescription>
         <soAccptsPatsInd>Y</soAccptsPatsInd>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>true</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528884125</telephoneNumber>
      <zip>554313538</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>10611 FRANCE AVE S</addressOne>
      <addressTwo>STE 200</addressTwo>
      <businessName>METRO DENTALCARE BLOOMINGTON</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.3747472468009176</distance>
      <facilityID>78</facilityID>
      <firstName>MEGAN</firstName>
      <genderCode>F</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>SCHWARTZBAUER</lastName>
      <latitude>44.81163309999999</latitude>
      <locationSid>275860542</locationSid>
      <longitude>-93.32905439999999</longitude>
      <middleInitial>R</middleInitial>
      <officeEmail>TYANBROCKLIN@AMDPI.COM</officeEmail>
      <officeLanguages>Persian</officeLanguages>
      <officeLanguages>Arabic</officeLanguages>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <products>
         <productCode>20</productCode>
         <productDescription>Delta Dental PPO</productDescription>
      </products>
      <products>
         <productCode>30</productCode>
         <productDescription>DeltaCare USA</productDescription>
         <soAccptsPatsInd>Y</soAccptsPatsInd>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>true</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528884125</telephoneNumber>
      <zip>554313538</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>10611 FRANCE AVE S</addressOne>
      <addressTwo>STE 201</addressTwo>
      <businessName>POINT FAMILY DENTISTRY PLLC</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.3747472468009176</distance>
      <facilityID>0</facilityID>
      <firstName>DANA</firstName>
      <genderCode>M</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>ISAACSON</lastName>
      <latitude>44.81163309999999</latitude>
      <locationSid>275880452</locationSid>
      <longitude>-93.32905439999999</longitude>
      <middleInitial>J</middleInitial>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528818404</telephoneNumber>
      <zip>554310000</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>10611 FRANCE AVE S</addressOne>
      <addressTwo>STE 200</addressTwo>
      <businessName>METRO DENTALCARE BLOOMINGTON</businessName>
      <city>BLOOMINGTON</city>
      <distance>2.3747472468009176</distance>
      <facilityID>0</facilityID>
      <firstName>SUANNE</firstName>
      <genderCode>F</genderCode>
      <handicappedAccessible>true</handicappedAccessible>
      <lastName>DULLARD</lastName>
      <latitude>44.81163309999999</latitude>
      <locationSid>275957717</locationSid>
      <longitude>-93.32905439999999</longitude>
      <officeEmail>TYANBROCKLIN@AMDPI.COM</officeEmail>
      <officeLanguages>Persian</officeLanguages>
      <officeLanguages>Arabic</officeLanguages>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>20</productCode>
         <productDescription>Delta Dental PPO</productDescription>
      </products>
      <products>
         <productCode>01</productCode>
         <productDescription>Delta Dental Premier</productDescription>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>true</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528884125</telephoneNumber>
      <zip>554313538</zip>
   </listOfLocations>
   <listOfLocations>
      <addressOne>4980 W 77th St</addressOne>
      <businessName>Metro Dentalcare Edina</businessName>
      <city>Edina</city>
      <distance>3.0484040085100346</distance>
      <facilityID>270</facilityID>
      <firstName>Jonathan</firstName>
      <handicappedAccessible>false</handicappedAccessible>
      <lastName>Moren</lastName>
      <latitude>44.864137</latitude>
      <locationSid>275976746</locationSid>
      <longitude>-93.347553</longitude>
      <officeServcDisableAdlt>false</officeServcDisableAdlt>
      <officeServcDisableChld>false</officeServcDisableChld>
      <products>
         <productCode>30</productCode>
         <productDescription>DeltaCare USA</productDescription>
         <soAccptsPatsInd>Y</soAccptsPatsInd>
      </products>
      <providerServcDisableAdlt>false</providerServcDisableAdlt>
      <providerServcDisableChld>false</providerServcDisableChld>
      <publicTransAvail>false</publicTransAvail>
      <specialties>General Dentist</specialties>
      <stateCode>MN</stateCode>
      <telephoneNumber>9528353383</telephoneNumber>
      <zip>55435</zip>
   </listOfLocations>
             <originatingLatitude>41.9464283</originatingLatitude>
             <originatingLongitude>-87.70740889999999</originatingLongitude>
             <status>PROVIDERS_FOUND</status>
            </WebServiceResult>
           </ns2:retrieveLocationsResponse>
          </S:Body>
         </S:Envelope>
'''

            //def xml = new XmlSlurper().parseText(responseXML)

            render(text: responseXML, contentType: "text/xml", encoding: "UTF-8")
        }

        def providerSearchResults() {

            SearchResults results = new SearchResults(
                    originatingLatitude:44.977,
                    originatingLongitude: -93.265,
                    alterRule:'NoFilter',
                    status:'PROVIDERS_FOUND',
                    geoCodeQuality:'RANGE_INTERPOLATED');

            results.providers.add(new Provider(
                    businessName : 'Hanks Hardware and Tooth Repair',
                    firstName : 'Alpha',
                    lastName : 'Male',
                    middleInitial : 'M',
                    suffix : 'DDS',
                    providerEmail : 'provider1@gmail.com',
                    genderCode : 'M',
                    providerServcDisableAdlt : true,
                    providerServcDisableChld : false)
            )
            results.providers[0].locations.add(new Location(
                    businessName : 'Hanks Hardware and Tooth Repair',
                    addressOne  :  '123 Seseme Street',
                    addressTwo  :  '',
                    stateCode  :  'MN',
                    city  :  'Minneapolis',
                    zip  :  '55438',
                    officeEmail  :  'location1@gmail.com',
                    urlText  :  'http://www.hanks-location1.com',
                    telephoneNumber  :  '(999)123-4567',
                    publicTransAvailable  :  true,
                    officeServcDisableAdlt  :  true,
                    officeServcDisableChld  :  true,
                    handicappedAccessible  :  true,
                    officeEmergencyMessage  :  'Not Sure What This Is',
                    facilityId  :  'Location1',
                    distance  :  100,
                    latitude  :  44.9778,
                    longitude : -93.2650)
            )
            results.providers[0].locations.add(new Location(
                    businessName : 'Hanks Hardware and Tooth Repair',
                    addressOne  :  '456 Other Street',
                    addressTwo  :  '',
                    stateCode  :  'MN',
                    city  :  'Minneapolis',
                    zip  :  '55439',
                    officeEmail  :  'location2@gmail.com',
                    urlText  :  'http://www.hanks-location2.com',
                    telephoneNumber  :  '(888)456-7890',
                    publicTransAvailable  :  true,
                    officeServcDisableAdlt  :  true,
                    officeServcDisableChld  :  true,
                    handicappedAccessible  :  true,
                    officeEmergencyMessage  :  'Not Sure What This Is',
                    facilityId  :  'Location2',
                    distance  :  200,
                    latitude  :  45.029327,
                    longitude : -93.362576)
            )
            results.providers[0].locations.add(new Location(
                    businessName : 'Hanks Hardware and Tooth Repair',
                    addressOne  :  '789 Yet Another Street',
                    addressTwo  :  '',
                    stateCode  :  'MN',
                    city  :  'Minneapolis',
                    zip  :  '55439',
                    officeEmail  :  'location2@gmail.com',
                    urlText  :  'http://www.hanks-location2.com',
                    telephoneNumber  :  '(888)456-7890',
                    publicTransAvailable  :  true,
                    officeServcDisableAdlt  :  true,
                    officeServcDisableChld  :  true,
                    handicappedAccessible  :  true,
                    officeEmergencyMessage  :  'Not Sure What This Is',
                    facilityId  :  'Location3',
                    distance  :  200,
                    latitude  :  44.9776,
                    longitude : -93.2652)
            )
            results.providers[0].locations.add(new Location(
                    businessName : 'Hanks Hardware and Tooth Repair',
                    addressOne  :  '291 Even Another Street',
                    addressTwo  :  '',
                    stateCode  :  'MN',
                    city  :  'Minneapolis',
                    zip  :  '55439',
                    officeEmail  :  'location2@gmail.com',
                    urlText  :  'http://www.hanks-location2.com',
                    telephoneNumber  :  '(888)456-7890',
                    publicTransAvailable  :  true,
                    officeServcDisableAdlt  :  true,
                    officeServcDisableChld  :  true,
                    handicappedAccessible  :  true,
                    officeEmergencyMessage  :  'Not Sure What This Is',
                    facilityId  :  'Location4',
                    distance  :  200,
                    latitude  :  44.9770,
                    longitude : -93.2659)
            )
            results.providers[0].locations.add(new Location(
                    businessName : 'Hanks Hardware and Tooth Repair',
                    addressOne  :  '382 Still Another Street',
                    addressTwo  :  '',
                    stateCode  :  'MN',
                    city  :  'Minneapolis',
                    zip  :  '55439',
                    officeEmail  :  'location2@gmail.com',
                    urlText  :  'http://www.hanks-location2.com',
                    telephoneNumber  :  '(888)456-7890',
                    publicTransAvailable  :  true,
                    officeServcDisableAdlt  :  true,
                    officeServcDisableChld  :  true,
                    handicappedAccessible  :  true,
                    officeEmergencyMessage  :  'Not Sure What This Is',
                    facilityId  :  'Location4',
                    distance  :  200,
                    latitude  :  44.9710,
                    longitude : -93.2699)
            )
            results.providers[0].products.add(new Product())
            results.providers[0].education.add("Degree 1, Education Place 1")
            results.providers[0].specialties.add("Speciality 1")
            results.providers[0].prvdrLanguages.add('English')
            results.providers[0].prvdrLanguages.add('Spanish')
            results.providers[0].officeTimes.add('EVE')
            results.providers[0].officeTimes.add('WKD')

            results.providers.add(new Provider(
                    businessName : 'Yet Another Provider',
                    firstName : 'Me',
                    lastName : 'Myself',
                    middleInitial : 'I',
                    suffix : 'DDS',
                    providerEmail : 'provider2@gmail.com',
                    genderCode : 'M',
                    providerServcDisableAdlt : true,
                    providerServcDisableChld : false)
            )
            results.providers[1].locations.add(new Location(
                    businessName : 'Yet Another Provider Location 1',
                    addressOne  :  '789 Whatever Street',
                    addressTwo  :  '',
                    stateCode  :  'MN',
                    city  :  'Minneapolis',
                    zip  :  '55438',
                    officeEmail  :  'provider2location1@gmail.com',
                    urlText  :  'http://www.another-location.com',
                    telephoneNumber  :  '(999)888-7777',
                    publicTransAvailable  :  true,
                    officeServcDisableAdlt  :  true,
                    officeServcDisableChld  :  true,
                    handicappedAccessible  :  true,
                    officeEmergencyMessage  :  'Not Sure What This Is',
                    facilityId  :  'Location1',
                    distance  :  100,
                    latitude  :  45.019387,
                    longitude : -93.392586)
            )

            results.providers[1].locations.add(new Location(
                    businessName : 'Yet Another Provider Location 2',
                    addressOne  :  '222 One Street',
                    addressTwo  :  '',
                    stateCode  :  'MN',
                    city  :  'Minneapolis',
                    zip  :  '55438',
                    officeEmail  :  'provider3location1@gmail.com',
                    urlText  :  'http://www.another-location.com',
                    telephoneNumber  :  '(888)999-6666',
                    publicTransAvailable  :  true,
                    officeServcDisableAdlt  :  true,
                    officeServcDisableChld  :  true,
                    handicappedAccessible  :  true,
                    officeEmergencyMessage  :  'Not Sure What This Is',
                    facilityId  :  'Location1',
                    distance  :  100,
                    latitude  :  44.995144,
                    longitude :  -93.395276)
            )

            results.providers[1].locations.add(new Location(
                    businessName : 'Yet Another Provider Location 3',
                    addressOne  :  '111 Two Street',
                    addressTwo  :  '',
                    stateCode  :  'MN',
                    city  :  'Minneapolis',
                    zip  :  '55438',
                    officeEmail  :  'provider3location1@gmail.com',
                    urlText  :  'http://www.another-location.com',
                    telephoneNumber  :  '(888)999-6666',
                    publicTransAvailable  :  true,
                    officeServcDisableAdlt  :  true,
                    officeServcDisableChld  :  true,
                    handicappedAccessible  :  true,
                    officeEmergencyMessage  :  'Not Sure What This Is',
                    facilityId  :  'Location1',
                    distance  :  100,
                    latitude  :  44.915144,
                    longitude :  -93.315276)
            )

            results.providers[1].products.add(new Product())
            results.providers[1].education.add("Degree 1, Education Place 1")
            results.providers[1].specialties.add("Speciality 1")
            results.providers[1].prvdrLanguages.add('English')
            results.providers[1].prvdrLanguages.add('Spanish')
            results.providers[1].officeTimes.add('EVE')
            results.providers[1].officeTimes.add('WKD')

            results.providers.add(new Provider(
                    businessName : 'A Third Provider',
                    firstName : 'Alpha',
                    lastName : 'Male',
                    middleInitial : 'M',
                    suffix : 'DDS',
                    providerEmail : 'provider1@gmail.com',
                    genderCode : 'M',
                    providerServcDisableAdlt : true,
                    providerServcDisableChld : false))

            results.providers[2].locations.add(new Location(
                    businessName : 'A Third Provider',
                    addressOne  :  '567 Third Street',
                    addressTwo  :  '',
                    stateCode  :  'MN',
                    city  :  'Minneapolis',
                    zip  :  '55438',
                    officeEmail  :  'provider3location1@gmail.com',
                    urlText  :  'http://www.another-location.com',
                    telephoneNumber  :  '(888)999-6666',
                    publicTransAvailable  :  true,
                    officeServcDisableAdlt  :  true,
                    officeServcDisableChld  :  true,
                    handicappedAccessible  :  true,
                    officeEmergencyMessage  :  'Not Sure What This Is',
                    facilityId  :  'Location1',
                    distance  :  100,
                    latitude  :  44.964144,
                    longitude :  -93.395276)
            );
            results.providers[2].locations.add(new Location(
                    businessName : 'A Third Provider',
                    addressOne  :  '888 Wow Street',
                    addressTwo  :  '',
                    stateCode  :  'MN',
                    city  :  'Minneapolis',
                    zip  :  '55438',
                    officeEmail  :  'provider3location1@gmail.com',
                    urlText  :  'http://www.another-location.com',
                    telephoneNumber  :  '(888)999-6666',
                    publicTransAvailable  :  true,
                    officeServcDisableAdlt  :  true,
                    officeServcDisableChld  :  true,
                    handicappedAccessible  :  true,
                    officeEmergencyMessage  :  'Not Sure What This Is',
                    facilityId  :  'Location1',
                    distance  :  100,
                    latitude  :  44.960144,
                    longitude :  -93.305276)
            );
            results.providers[2].locations.add(new Location(
                    businessName : 'A Third Provider',
                    addressOne  :  '777 Cool Street',
                    addressTwo  :  '',
                    stateCode  :  'MN',
                    city  :  'Minneapolis',
                    zip  :  '55438',
                    officeEmail  :  'provider3location1@gmail.com',
                    urlText  :  'http://www.another-location.com',
                    telephoneNumber  :  '(888)999-6666',
                    publicTransAvailable  :  true,
                    officeServcDisableAdlt  :  true,
                    officeServcDisableChld  :  true,
                    handicappedAccessible  :  true,
                    officeEmergencyMessage  :  'Not Sure What This Is',
                    facilityId  :  'Location1',
                    distance  :  100,
                    latitude  :  44.965144,
                    longitude :  -93.355276)
            );


            results.providers[2].products.add(new Product())
            results.providers[2].education.add("Degree 1, Education Place 1")
            results.providers[2].specialties.add("Speciality 1")
            results.providers[2].prvdrLanguages.add('English')
            results.providers[2].prvdrLanguages.add('Spanish')
            results.providers[2].officeTimes.add('EVE')
            results.providers[2].officeTimes.add('WKD')

            render results as JSON

        }
    }

