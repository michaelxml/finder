package location

class Location {

    String firstName = ''
    String lastName = ''
    String middleInitial = ''
    String genderCode = ''
    String businessName = ''
    String addressOne = ''
    String addressTwo = ''
    String stateCode = ''
    String city = ''
    String zip = ''
    String officeEmail = ''
    String urlText = ''
    String telephoneNumber = ''
    boolean publicTransAvailable = false
    boolean providerServcDisableChld = false
    boolean providerServcDisableAdlt= false
    boolean officeServcDisableChld = false
    boolean officeServcDisableAdlt= false
    boolean handicappedAccessible = false
    String officeEmergencyMessage = ''
    String facilityID = ''
    String distance
    String latitude
    String longitude
    def products = new ArrayList<Product>();
    def education = new ArrayList<String>();
    def specialties = new ArrayList<String>();
    def prvdrLanguages = new ArrayList<String>();
    def officeLanguages = new ArrayList<String>();
    def officeTimes = new ArrayList<String>();

}
