package com.copo.utils.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Web Tools
 *
 * @author Junbang Huang
 * @version 2014-6-17
 */
public final class NetUtils {

    protected static final Logger LOGGER = LoggerFactory
            .getLogger(NetUtils.class);

    private NetUtils() {

    }

    /**
     * PING to check if address available
     *
     * @param address
     * @return
     */
    public static boolean pingUrl(final String address) {

        try {

            final URL url = new URL("http://" + address);

            final HttpURLConnection urlConn = (HttpURLConnection) url
                    .openConnection();

            urlConn.setConnectTimeout(1000 * 10); // mTimeout is in seconds

            final long startTime = System.currentTimeMillis();

            urlConn.connect();

            final long endTime = System.currentTimeMillis();

            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                LOGGER.info("Time (ms) : " + (endTime - startTime));
                LOGGER.info("Ping to " + address + " was success");
                return true;
            }

        } catch (final MalformedURLException e1) {
            e1.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
