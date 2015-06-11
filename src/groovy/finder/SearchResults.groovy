package finder

class SearchResults {

        String originatingLongitude
        String originatingLatitude
        String alterRule
        String status
        String geoCodeQuality

        def locations = new ArrayList<Location>();

        def loadFromXML(def webServiceResult) {

                if (webServiceResult) {
                        originatingLongitude = webServiceResult.originatingLongitude?.text()
                        originatingLatitude = webServiceResult.originatingLatitude?.text()
                        alterRule = webServiceResult.alterRule?.text()
                        status = webServiceResult.status?.text()
                        geoCodeQuality = webServiceResult.geoCodeQuality?.text()
                        def locElements = webServiceResult.listOfLocations.findAll { it.name() == 'listOfLocations' }
                        locElements.each{
                                def loc = new Location()
                                loc.firstName = it.firstName?.text()
                                loc.lastName = it.lastName?.text()
                                loc.middleInitial = it.middleInitial?.text()
                                loc.genderCode = it.genderCode?.text()
                                loc.businessName = it.businessName?.text()
                                loc.addressOne = it.addressOne?.text()
                                loc.addressTwo = it.addressTwo?.text()
                                loc.stateCode = it.stateCode?.text()
                                loc.city = it.city?.text()
                                loc.zip = it.zip?.text()
                                loc.officeEmail = it.officeEmail?.text()
                                loc.urlText = it.urlText?.text()
                                loc.telephoneNumber = it.telephoneNumber?.text()
                                loc.publicTransAvailable =  it.publicTransAvailable?.text()
                                loc.providerServcDisableChld =  it.providerServcDisableChld?.text()
                                loc.providerServcDisableAdlt=  it.providerServcDisableAdl?.text()
                                loc.officeServcDisableChld = it.officeServcDisableChld?.text()
                                loc.officeServcDisableAdlt=it.officeServcDisableAdlt?.text()
                                loc.handicappedAccessible =  it.handicappedAccessible?.text()
                                loc.officeEmergencyMessage = it.officeEmergencyMessage?.text()
                                loc.facilityID = it.facilityID?.text()
                                loc.distance = it.distance?.text()
                                loc.latitude = it.alatitude?.text()
                                loc.longitude = it.longitude?.text()
                                def locProducts = it.products?.findAll { it.name() == 'products' }
                                def locEducation = it.education?.findAll { it.name() == 'education' }
                                def locSpecialties = it.specialties?.findAll { it.name() == 'specialties' }
                                def locPrvdrLanguages = it.prvdrLanguages?.findAll { it.name() == 'prvdrLanguages' }
                                def locOfficeLanguages = it.officeLanguages?.findAll { it.name() == 'officeLanguages' }
                                def locOfficeTimes = it.officeTimes?.findAll { it.name() == 'officeTimes' }
                                locProducts.each {
                                  def prod = new Product()
                                        prod.productCode = it.productCode?.text()
                                        prod.productDescription = it.productDescription?.text()
                                        prod.prvdAccptsPatsInd = it.prvdAccptsPatsInd?.text()
                                        prod.soAccptsPatsInd = it.soAccptsPatsInd?.text()
                                        loc.products.add(prod)
                                }
                                locEducation.each {
                                   loc.education.add(it.text())
                                }
                                locSpecialties.each {
                                        loc.specialties.add(it.text())
                                }
                                locPrvdrLanguages.each {
                                        loc.prvdrLanguages.add(it.text())
                                }
                                locOfficeLanguages.each {
                                        loc.officeLanguages.add(it.text())
                                }
                                locOfficeTimes.each {
                                        loc.officeTimes.add(it.text())
                                }

                                locations.add(loc)
                        }

                }
        }

}
