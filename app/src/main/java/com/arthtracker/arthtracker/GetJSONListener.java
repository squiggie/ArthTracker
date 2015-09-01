package com.arthtracker.arthtracker;

import org.json.JSONObject;

public interface GetJSONListener {
    public void onRemoteCallComplete(JSONObject jsonFromNet);
}
