package com.lew.mapleleaf.db.dao;

import android.content.ContentValues;

import java.util.List;
import java.util.Map;

import com.lew.mapleleaf.beans.ChannelItem;

public interface ChannelDaoInterface {
    boolean addCache(ChannelItem item);

    boolean deleteCache(String whereClause, String[] whereArgs);

    boolean updateCache(ContentValues values, String whereClause, String[] whereArgs);

    Map<String, String> viewCache(String selection, String[] selectionArgs);

    List<Map<String, String>> listCache(String selection, String[] selectionArgs);

    void clearFeedTable();
}
