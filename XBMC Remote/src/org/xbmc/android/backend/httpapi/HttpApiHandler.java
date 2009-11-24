/*
 *      Copyright (C) 2005-2009 Team XBMC
 *      http://xbmc.org
 *
 *  This Program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2, or (at your option)
 *  any later version.
 *
 *  This Program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with XBMC Remote; see the file license.  If not, write to
 *  the Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139, USA.
 *  http://www.gnu.org/copyleft/gpl.html
 *
 */

package org.xbmc.android.backend.httpapi;

import org.xbmc.android.remote.R;
import org.xbmc.httpapi.type.CacheType;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Basically contains two things:
 * <ul>
 *   <li>The callback code of a completed HTTP API command</li>
 *   <li>The result of the HTTP API command</li>
 * </ul>
 * 
 * @author Team XBMC
 * @param <T> Type of the API command's result
 */
public class HttpApiHandler<T> implements Runnable {
	public T value;
	public int defaultCover = R.drawable.icon_genre;
	protected int cacheType;
	
	protected final Activity mActivity;
	protected final long mTag;
	
	public HttpApiHandler(HttpApiHandler<T> handler) {
		mTag = handler.mTag;
		mActivity = handler.mActivity;
		defaultCover = handler.defaultCover;
	}
	public HttpApiHandler(Activity activity) {
		mActivity = activity;
		mTag = 0;
	}
	public HttpApiHandler(Activity activity, long tag) {
		mActivity = activity;
		mTag = tag;
	}
	public HttpApiHandler(Activity activity, long tag, int defaultCover) {
		mActivity = activity;
		mTag = tag;
		this.defaultCover = defaultCover;
	}
	public Activity getActivity() {
		return mActivity;
	}
	public Bitmap getDefaultCover() {
		return BitmapFactory.decodeResource(mActivity.getResources(), defaultCover);
	}
	public void run () {
		// do nothing if not overloaded
	}
	/**
	 * Executed before downloading large files. Overload and return false to 
	 * skip downloading, for instance when a list with covers is scrolling.
	 * @return
	 */
	public boolean postCache() {
		return true;
	}
}