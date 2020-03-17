package tp1.androidproject.lifequality.Utils;

import java.util.concurrent.TimeUnit;

public class Constants {
    public static final String UrlCitySearch = "https://api.teleport.org/api/cities/?search=";
    public static final String ISSUE_DISPLAY = "Sorry, there has been an issue...";
    public static final String ISSUE_DISPLAY_IMAGE = "Sorry, there has been an issue with the image...";
    public static final String FLICKR_URL = "https://www.flickr.com/services/feeds/photos_public.gne?tags=" ;
    public static final long HOUR_IN_MILLIS = TimeUnit.HOURS.toMillis(1);
    public static final long MIN_IN_MILLIS = TimeUnit.MINUTES.toMillis(1);
    public static final long INTERVAL_MIN = HOUR_IN_MILLIS * 2;
    public static final long INTERVAL_MAX = HOUR_IN_MILLIS * 2 + MIN_IN_MILLIS*30;
}
