package net.kuronekoserver.reactrolebot.api.panel;

import com.google.gson.reflect.TypeToken;
import net.kuronekoserver.reactrolebot.api.Repository;
import net.kuronekoserver.reactrolebot.api.RequestFormat;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PanelRepository extends Repository<PanelEntity> {

    public PanelRepository() {
        super("http://localhost:3000", "panels");
    }

    @Override
    public Type getArrayType() {
        return new TypeToken<RequestFormat<ArrayList<PanelEntity>>>() {}.getType();
    }

    @Override
    public Type getType() {
        return new TypeToken<RequestFormat<PanelEntity>>() {}.getType();
    }
}
