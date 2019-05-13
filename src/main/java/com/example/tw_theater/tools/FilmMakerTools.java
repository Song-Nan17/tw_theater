package com.example.tw_theater.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.tw_theater.model.FilmMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilmMakerTools {

    public List<FilmMaker> generateFilmMakers(JSONArray filmMakersStr) {
        List<FilmMaker> filmMakers = new ArrayList<>();
        for (int i = 0; i < filmMakersStr.size(); i++) {
            JSONObject filmMakerStr = filmMakersStr.getJSONObject(i);
            FilmMaker filmMaker = generateFilmMaker(filmMakerStr);
            filmMakers.add(filmMaker);
        }
        return filmMakers;
    }

    FilmMaker generateFilmMaker(JSONObject filmMakerStr) {
        String userId = filmMakerStr.getString("id");
        String name = filmMakerStr.getString("name");
        String alt = filmMakerStr.getString("alt");
        Optional<JSONObject> optionalAvatars = Optional.ofNullable(filmMakerStr.getJSONObject("avatars"));
        JSONObject avatars = optionalAvatars.orElse(JSON.parseObject("{\"small\": null}"));
        String image = avatars.getString("small");

        FilmMaker filmMaker = new FilmMaker();
        filmMaker.setUserId(userId);
        filmMaker.setName(name);
        filmMaker.setAlt(alt);
        filmMaker.setImage(image);
        return filmMaker;
    }
}
