
//This class was made to put a single class instead of multiple variables inside each root.
public class Country {
    String CountryName;
    String population;
    String capital_city;
    String largest_city;
    String official_language;
    String currency;
    Country(String CountryName,
    String population,
    String capital_city,
    String largest_city,
    String official_language,
    String currency){
        this.CountryName=CountryName;
        this.population=population;
        this.capital_city=capital_city;
        this.largest_city=largest_city;
        this.official_language=official_language;
        this.currency=currency;
    }
    @Override
    public String toString(){
        return CountryName+"  "+population+"  "+capital_city+"  "+largest_city+"  "+official_language+"  "+currency+"  ";
    }
}
