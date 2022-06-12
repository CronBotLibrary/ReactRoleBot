package net.kuronekoserver.reactrolebot.api.role;

import com.google.gson.reflect.TypeToken;
import net.kuronekoserver.reactrolebot.api.Repository;
import net.kuronekoserver.reactrolebot.api.RequestFormat;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RoleRepository extends Repository<RoleEntity> {

    public RoleRepository() {
        super("http://localhost:3000", "roles");
    }

    @Override
    public Type getArrayType() {
        return new TypeToken<RequestFormat<ArrayList<RoleEntity>>>() {}.getType();
    }

    @Override
    public Type getType() {
        return new TypeToken<RequestFormat<RoleEntity>>() {}.getType();
    }
}
