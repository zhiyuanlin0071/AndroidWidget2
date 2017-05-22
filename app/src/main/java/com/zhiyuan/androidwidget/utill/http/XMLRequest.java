package com.zhiyuan.androidwidget.utill.http;

import android.util.Xml;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import static android.R.attr.data;

/**
 * Created by stefan on 2017/5/22.
 */

public class XMLRequest extends Request<XmlPullParser> {
	private Response.Listener<XmlPullParser> mlistener;
	
	public XMLRequest(String url, Response.Listener<XmlPullParser> listener, Response.ErrorListener errorlistener) {
		this(0, url, listener, errorlistener);
	}
	
	public XMLRequest(int method, String url, Response.Listener<XmlPullParser> listener, Response.ErrorListener errorlistener) {
		super(method, url, errorlistener);
		mlistener = listener;
	}
	
	@Override
	protected Response<XmlPullParser> parseNetworkResponse(NetworkResponse networkResponse) {
		String parsed;
		try {
			parsed = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
			XmlPullParser xmlPullParser = Xml.newPullParser();
			InputStream is = new ByteArrayInputStream(parsed.getBytes());
			xmlPullParser.setInput(is, "UTF-8");
			return Response.success(xmlPullParser, HttpHeaderParser.parseCacheHeaders(networkResponse));
		} catch (UnsupportedEncodingException var4) {
			return Response.error(new ParseError(var4));
		} catch (XmlPullParserException e) {
			return Response.error(new ParseError(e));
		}
	}
	
	@Override
	protected void deliverResponse(XmlPullParser s) {
		mlistener.onResponse(s);
	}
}
