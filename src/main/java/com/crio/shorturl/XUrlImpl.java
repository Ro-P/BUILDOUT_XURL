package com.crio.shorturl;
import java.util.HashMap;
import java.util.UUID;



class XUrlImpl implements XUrl {
    private HashMap<String,String> shortToLongUrl = new HashMap<String,String>();
    private HashMap<String,String> longToShortUrl = new HashMap<String,String>();
    private HashMap<String ,Integer> hitCount = new HashMap<String,Integer>();
    public String registerNewUrl(String longUrl) {

        //Check if longURL exists
        for(String e: longToShortUrl.keySet()){
            if(e.equals(longUrl)){
               // System.out.println(longToShortUrl.get(e));
               if(hitCount.get(longUrl)==null){
                hitCount.put(longUrl,0);
            }
            hitCount.replace(longUrl,hitCount.get(longUrl)+1);

                return longToShortUrl.get(e);
            }
        }

       String newURL = "http://short.url/";
        //Generate an unique alphanumeric 9 digit value 
        String uniqueId= UUID.randomUUID().toString().replace("-", "").substring(0,9);
        String shortUrl = newURL + uniqueId;


        //add it to the map
        shortToLongUrl.put(shortUrl,longUrl);
        longToShortUrl.put(longUrl,shortUrl);

        //return the short URL 
        return shortUrl;
        
    }

    
    public String registerNewUrl(String longUrl, String shortUrl){
        //check if shorturl exists
        for(String e: shortToLongUrl.keySet()){
            if(e.equals(shortUrl)){
  
                return null;
            }
        }


        shortToLongUrl.put(shortUrl,longUrl);
        longToShortUrl.put(longUrl,shortUrl);
        return shortUrl;

    }

    public String getUrl(String shortUrl){

       String longURL = shortToLongUrl.get(shortUrl);
        if(hitCount.get(longURL)==null){
            hitCount.put(longURL,0);  
        }
        else{
        hitCount.replace(longURL,hitCount.get(longURL)+1);
        }
        return longURL;
   
    }

    
    public Integer getHitCount(String longUrl){
        if(hitCount.containsKey(longUrl)){
       return hitCount.get(longUrl);
    }else{
        return 0;
    }
    }


    public String delete(String longUrl){
        String shrtURL = longToShortUrl.get(longUrl);
        shortToLongUrl.remove(shrtURL);
        longToShortUrl.remove(longUrl);
        return longUrl;
        
    }


}