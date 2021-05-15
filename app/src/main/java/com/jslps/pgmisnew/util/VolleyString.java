package com.jslps.pgmisnew.util;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;


import java.util.Hashtable;
import java.util.Map;

public class VolleyString {

    private StringRequest mStringRequest;
    private String url;
    private String tableIndentifier;
    VolleyListner volleyListner;

    public interface VolleyListner{
        void onResponseSuccess(String tableIndentifier,String result);
        void onResponseFailure(String tableIdentifier);
    }

    public VolleyString( String url, String tableIndentifier,VolleyListner volleyListner) {
        this.tableIndentifier=tableIndentifier;
        this.url=url;
        this.volleyListner = volleyListner;
    }

    public StringRequest getString(){
        mStringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">")+2);
                    String result = XmlString.replaceAll("</string>","");
                    volleyListner.onResponseSuccess(tableIndentifier,result);
                    },
                error -> {

                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                return null;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return mStringRequest;
    }

    public StringRequest postmeeting(String json){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">") + 2);
                    String result = XmlString.replaceAll("</string>", "");
                    volleyListner.onResponseSuccess(tableIndentifier, result);
                },
                error -> {
                }
                )
               {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("sData", json);
                return params;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return mStringRequest;
    }

    public StringRequest postpaymenttrans(String json){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">") + 2);
                    String result = XmlString.replaceAll("</string>", "");
                    volleyListner.onResponseSuccess(tableIndentifier, result);
                },
                error -> {

                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("sData", json);
                return params;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return mStringRequest;
    }

    public StringRequest postsharecapital(String json){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">") + 2);
                    String result = XmlString.replaceAll("</string>", "");
                    volleyListner.onResponseSuccess(tableIndentifier, result);
                },
                error -> {
                }
            )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("sData", json);
                return params;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return mStringRequest;
    }

    public StringRequest postbrscall(String json){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">") + 2);
                    String result = XmlString.replaceAll("</string>", "");
                    volleyListner.onResponseSuccess(tableIndentifier, result);
                },
                error -> {
                })
             {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("sData", json);
                return params;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return mStringRequest;
    }

    public StringRequest postMembershipFee(String json){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">") + 2);
                    String result = XmlString.replaceAll("</string>", "");
                    volleyListner.onResponseSuccess(tableIndentifier, result);
                },
                error -> {
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("sData", json);
                return params;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return mStringRequest;
    }

    public StringRequest postBrstransaddsub(String json){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">") + 2);
                    String result = XmlString.replaceAll("</string>", "");
                    volleyListner.onResponseSuccess(tableIndentifier, result);
                },
                error -> {
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("sData", json);
                return params;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return mStringRequest;
    }

    public StringRequest postPhInput(String json){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">") + 2);
                    String result = XmlString.replaceAll("</string>", "");
                    volleyListner.onResponseSuccess(tableIndentifier, result);
                },
                error -> {
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("sData", json);
                return params;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return mStringRequest;
    }

    public StringRequest postPgReceiptTrans(String json){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">") + 2);
                    String result = XmlString.replaceAll("</string>", "");
                    volleyListner.onResponseSuccess(tableIndentifier, result);
                },
                error -> {
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("sData", json);
                return params;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return mStringRequest;
    }

    public StringRequest postItempurchasedbypg(String json){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">") + 2);
                    String result = XmlString.replaceAll("</string>", "");
                    volleyListner.onResponseSuccess(tableIndentifier, result);
                },
                error -> {
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("sData", json);
                return params;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return mStringRequest;
    }

    public StringRequest postPgmisBatchLoan(String json){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">") + 2);
                    String result = XmlString.replaceAll("</string>", "");
                    volleyListner.onResponseSuccess(tableIndentifier, result);
                },
                error -> {
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("sData", json);
                return params;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return mStringRequest;
    }

    public StringRequest postPgmisloanrepayment(String json){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">") + 2);
                    String result = XmlString.replaceAll("</string>", "");
                    volleyListner.onResponseSuccess(tableIndentifier, result);
                },
                error -> {
                })
         {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("sData", json);
                return params;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return mStringRequest;
    }

    public StringRequest postPgmisBrsImage(String json,String json2){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">") + 2);
                    String result = XmlString.replaceAll("</string>", "");
                    volleyListner.onResponseSuccess(tableIndentifier, result);
                },
                error -> {
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("filebytes", json);
                params.put("fileName",json2);
                params.put("sUID","");
                params.put("sImageFieldName","");
                return params;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return mStringRequest;
    }

    public StringRequest postPgmisBankwithdrawcashdeposit(String json){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">") + 2);
                    String result = XmlString.replaceAll("</string>", "");
                    volleyListner.onResponseSuccess(tableIndentifier, result);
                },
                error -> {
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("sData", json);
                return params;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return mStringRequest;
    }
    public StringRequest postPgmisChequeBank(String json){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String XmlString = response.substring(response.indexOf("\">") + 2);
                    String result = XmlString.replaceAll("</string>", "");
                    volleyListner.onResponseSuccess(tableIndentifier, result);
                },
                error -> {
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("sData", json);
                return params;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return mStringRequest;
    }

}
