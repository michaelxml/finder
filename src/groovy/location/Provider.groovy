package location

class Provider {

    String businessName = 'Default'
    String firstName = 'Default'
    String lastName = 'Default'
    String middleInitial = 'Default'
    String suffix = 'Default'
    String providerEmail = 'Default'
    String genderCode = 'Default'
    boolean providerServcDisableAdlt = true
    boolean providerServcDisableChld = true
    def locations = new ArrayList<Location>();
    def products = new ArrayList<Product>();
    def education = new ArrayList<String>();
    def specialties = new ArrayList<String>();
    def prvdrLanguages = new ArrayList<String>();
    def officeTimes = new ArrayList<String>();

}