package com.example.roomdb;

import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class RetrieveFeed extends AsyncTask {

    URL url;

    ArrayList<Item> items = new ArrayList<>();
    @Override
    protected Object doInBackground(Object[] objects) {
        // Initializing instance variables
        try {
            url = new URL("http://192.168.2.151:3161/devices/AdvanReader-m4-150/inventory");

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            // We will get the XML from an input stream
            xpp.setInput(getInputStream(url), "UTF_8");
            processParsing(xpp);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }


    public InputStream getInputStream(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        InputStream strm=urlConnection.getInputStream();
        return strm;
    }

    private void processParsing(XmlPullParser parser) throws IOException, XmlPullParserException{

        int eventType = parser.getEventType();
        Item currentItem = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String eltName = null;

            switch (eventType) {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();
                    if ("item".equals(eltName)) {
                        currentItem = new Item();
                        items.add(currentItem);
                    } else if (currentItem != null) {
                        if ("ts".equals(eltName)) {
                            currentItem.setTimestamp(parser.nextText());
                        } else if ("epc".equals(eltName)) {
                            int id= Integer.parseInt(parser.nextText().substring(23));
                            currentItem.setItemID(id);

                        }
                        else if ("prop".equals(eltName)){
                            String porttocheck = parser.nextText();
                            if("ANTENNA_PORT:1,".equals(porttocheck)){
                                currentItem.setPort(1);
                            }
                            if("ANTENNA_PORT:2,".equals(porttocheck)){
                                currentItem.setPort(2);
                            }
                            if("ANTENNA_PORT:3,".equals(porttocheck)){
                                currentItem.setPort(3);
                            }
                            if("ANTENNA_PORT:4,".equals(porttocheck)){
                                currentItem.setPort(4);
                            }
                        }
                        currentItem.setIsActive(true);
                    }

                    break;

            }

            eventType = parser.next();

        }

    }

    public ArrayList<Item> items()
    {
        return items;
    }

}
