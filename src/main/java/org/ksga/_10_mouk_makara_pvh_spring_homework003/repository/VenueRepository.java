package org.ksga._10_mouk_makara_pvh_spring_homework003.repository;

import org.apache.ibatis.annotations.*;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Venue;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.VenueRequest;

import java.util.List;

@Mapper
public interface VenueRepository {
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
    })
    @Select("""
            SELECT * FROM venues OFFSET #{offset} LIMIT #{size}
            """)
    List<Venue> getAllVenues(Long offset, Long size);

    @ResultMap("venueMapper")
    @Select("""
    INSERT INTO venues VALUES (default, #{req.venueName}, #{req.location}) RETURNING *
""")
    Venue createVenue(@Param("req") VenueRequest request);

    @ResultMap("venueMapper")
    @Select("""
    SELECT * FROM venues WHERE venue_name = #{name} LIMIT 1
""")
    Venue findVenueByName(@Param("name") String name);

    @ResultMap("venueMapper")
    @Select("""
    SELECT * FROM venues WHERE venue_id = #{venueId}
""")
    Venue getVenueById(Long venueId);

    @ResultMap("venueMapper")
    @Select("""
    DELETE FROM venues WHERE venue_id = #{venueId}
""")
    Venue deleteVenueById(Long venueId);

    @ResultMap("venueMapper")
    @Select("""
    UPDATE venues SET venue_name = #{req.venueName}, location = #{req.location} WHERE venue_id = #{venueId} RETURNING *
""")
    Venue updateVenueById(Long venueId,@Param("req") VenueRequest request);
}
