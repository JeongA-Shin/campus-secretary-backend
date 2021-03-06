package group.campussecretary.feature.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProfile is a Querydsl query type for Profile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProfile extends EntityPathBase<Profile> {

    private static final long serialVersionUID = 912792104L;

    public static final QProfile profile = new QProfile("profile");

    public final StringPath briefingTime = createString("briefingTime");

    public final StringPath calendar = createString("calendar");

    public final StringPath campusDay = createString("campusDay");

    public final StringPath newsCount = createString("newsCount");

    public final StringPath newsKeyWordList = createString("newsKeyWordList");

    public final StringPath newsSearch = createString("newsSearch");

    public final ComparablePath<java.util.UUID> profileId = createComparable("profileId", java.util.UUID.class);

    public final StringPath scheduleCount = createString("scheduleCount");

    public final StringPath weather = createString("weather");

    public QProfile(String variable) {
        super(Profile.class, forVariable(variable));
    }

    public QProfile(Path<? extends Profile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfile(PathMetadata metadata) {
        super(Profile.class, metadata);
    }

}

