/*
 * Created by Cristian Gonzalez on 15/12/23 13:38
 *  Copyright (c) NetFreeMexico 2023 . All rights reserved.
 */

package com.netfreemexico.generador;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;

import com.netfreemexico.generador.constants.AppConstants;

import org.json.JSONArray;
import org.json.JSONObject;

public class NetFreeMXGen {
    private static final String TAG = "NetFreeMXGen";
    @SuppressLint("StaticFieldLeak")
    private static NetFreeMXGen instance;
    private boolean tokenMode = false;
    private Context context;
    private String tokenPassword = "";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private NetFreeMXGen() {}

    public static NetFreeMXGen getInstance() {
        if (instance == null) {
            instance = new NetFreeMXGen();
        }
        return instance;
    }

    public void init(Context context, SharedPreferences prefs, boolean isTokenMode) {
        this.context = context;
        this.prefs = prefs;
        editor = prefs.edit();
        tokenMode = isTokenMode;
        tokenPassword = getTokenId();
    }

    public void loadServer(JSONArray servers) {
        try {
            int position = prefs.getInt(AppConstants.SERVER_POSITION, 0);
            Log.i(TAG, "POSICION " + position);
            
            JSONObject server = servers.getJSONObject(position);
            int serverType = server.getInt("serverType");

            switch (serverType) {
                case AppConstants.SERVER_TYPE_SSH:
                    loadSSH(server);
                    break;
                case AppConstants.SERVER_TYPE_SLOWDNS:
                    loadSlowDns(server);
                    break;
                case AppConstants.SERVER_TYPE_UDP:
                    loadUDP(server);
                    break;
                case AppConstants.SERVER_TYPE_V2RAY:
                    loadV2ray(server);
                    break;
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void setTokenPassword(String password) {
        tokenPassword = password;
    }

    private void loadSSH(JSONObject server) throws Exception {
        int tunnelType = server.getInt("tunnelType");
        String serverHost = server.getString("ServerHost");
        String serverPort = server.getString("ServerPort");
        String proxyHost = server.getString("ProxyHost");
        String proxyPort = server.getString("ProxyPort");
        String payload = server.getString("Payload");
        String sni = server.getString("Sni");
        String username = server.getString("Username");
        String password = server.getString("Password");

        editor.putString(AppConstants.SERVIDOR_KEY, serverHost);
        editor.putString(AppConstants.SERVIDOR_PORTA_KEY, serverPort);
        editor.putString(AppConstants.PROXY_IP_KEY, proxyHost);
        editor.putString(AppConstants.PROXY_PORTA_KEY, proxyPort);
        editor.putString(AppConstants.CUSTOM_PAYLOAD_KEY, payload);
        editor.putString(AppConstants.CUSTOM_SNI, sni);

        if (tokenMode) {
            String user = tokenModeActive() ? getTokenId() : getUsername();
            String pass = tokenModeActive() ? tokenPassword : getPassword();

            editor.putString(AppConstants.USUARIO_KEY, user);
            editor.putString(AppConstants.SENHA_KEY, pass);
        } else {
            editor.putString(AppConstants.USUARIO_KEY, username);
            editor.putString(AppConstants.SENHA_KEY, password);
        }

        switch (tunnelType) {
            case AppConstants.TUNNEL_TYPE_DIRECT:
                editor.putBoolean(AppConstants.PROXY_USAR_DEFAULT_PAYLOAD, false);
                editor.putInt(AppConstants.TUNNELTYPE_KEY, AppConstants.bTUNNEL_TYPE_SSH_DIRECT);

                Log.i(TAG, "SELECTED TUNNEL_DIRECT");
                break;
            case AppConstants.TUNNEL_TYPE_PROXY:
                editor.putBoolean(AppConstants.PROXY_USAR_DEFAULT_PAYLOAD, false);
                editor.putInt(AppConstants.TUNNELTYPE_KEY, AppConstants.bTUNNEL_TYPE_SSH_PROXY);

                Log.i(TAG, "SELECTED TUNNEL PROXY");
                break;
            case AppConstants.TUNNEL_TYPE_SSHSSL:
                editor.putBoolean(AppConstants.PROXY_USAR_DEFAULT_PAYLOAD, true);
                editor.putInt(AppConstants.TUNNELTYPE_KEY, AppConstants.bTUNNEL_TYPE_SSH_SSL);

                Log.i(TAG, "SELECTED TUNNEL SSH SSL");
                break;
            case AppConstants.TUNNEL_TYPE_SSLPAYLOAD:
                editor.putBoolean(AppConstants.PROXY_USAR_DEFAULT_PAYLOAD, false);
                editor.putInt(AppConstants.TUNNELTYPE_KEY, AppConstants.bTUNNEL_TYPE_SSL_PAYLOAD);

                Log.i(TAG, "SELECTED TUNNEL SSLPAYLOAD");
                break;
        }

        editor.apply();

        Log.i(TAG, "SELECTED SSH");
    }

    private void loadSlowDns(JSONObject server) throws Exception {
        String pubKey = server.getString("PubKey");
        String nameServery = server.getString("NameServer");
        String dns = server.getString("Dns");
        String serverHost = server.getString("ServerHost");
        String serverPort = server.getString("ServerPort");

        editor.putString(AppConstants.CHAVE_KEY, pubKey);

        editor.putString(AppConstants.NAMESERVER_KEY, nameServery);
        editor.putString(AppConstants.DNS_KEY, dns);

        editor.putString(AppConstants.SERVIDOR_KEY, serverHost);
        editor.putString(AppConstants.SERVIDOR_PORTA_KEY, serverPort);

        editor.putBoolean(AppConstants.PROXY_USAR_DEFAULT_PAYLOAD, true);
        editor.putInt(AppConstants.TUNNELTYPE_KEY, AppConstants.bTUNNEL_TYPE_SLOWDNS);
        editor.apply();

        Log.i(TAG, "SELECTED SLOW DNS");
    }

    private void loadUDP(JSONObject server) throws Exception {
        String udpServer = server.getString("UdpServer");
        String udpAuth = server.getString("UdpAuth");
        String udpObfs = server.getString("UdpObfs");
        String udpBuffer = server.getString("UdpBuffer");
        String udpUp = server.getString("UdpUp");
        String udpDown = server.getString("UdpDown");

        editor.putString(AppConstants.UDP_SERVER, udpServer);

        editor.putString(AppConstants.UDP_AUTH, udpAuth);
        editor.putString(AppConstants.UDP_OBFS, udpObfs);


        editor.putString(AppConstants.UDP_BUFFER, udpBuffer);
        editor.putString(AppConstants.UDP_UP, udpUp);

        editor.putString(AppConstants.UDP_DOWN, udpDown);
        editor.putInt(AppConstants.TUNNELTYPE_KEY, AppConstants.bTUNNEL_TYPE_UDP);
        editor.apply();

        Log.i(TAG, "SELECTED UDP");
    }

    private void loadV2ray(JSONObject server) throws Exception {
        String v2rayJson = server.getString("v2ray");
        editor.putString(AppConstants.V2RAY_JSON, v2rayJson);
        editor.putInt(AppConstants.TUNNELTYPE_KEY, AppConstants.bTUNNEL_TYPE_V2RAY);
        editor.apply();

        Log.i(TAG, "SELECTED V2RAY");
    }

    @SuppressLint("HardwareIds")
    private String getTokenId() {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    @SuppressWarnings("deprecation")
    private String getUsername() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(AppConstants.USER_LOGIN, "");
    }

    @SuppressWarnings("deprecation")
    private String getPassword() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(AppConstants.PASSW_LOGIN, "");
    }

    @SuppressWarnings("deprecation")
    private boolean tokenModeActive() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(AppConstants.USER_HWID, false);
    }


}
