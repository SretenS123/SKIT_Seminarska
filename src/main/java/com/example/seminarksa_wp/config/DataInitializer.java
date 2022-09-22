package com.example.seminarksa_wp.config;

import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.Rating;
import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.model.Venue;
import com.example.seminarksa_wp.model.enumerations.EventType;
import com.example.seminarksa_wp.model.enumerations.Role;
import com.example.seminarksa_wp.service.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class DataInitializer {

    private final UserService userService;
    private final TicketService ticketService;
    private final EventService eventService;
    private final VenueService venueService;
    private final RatingService ratingService;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserService userService, TicketService ticketService, EventService eventService, VenueService venueService, RatingService ratingService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.eventService = eventService;
        this.venueService = venueService;
        this.ratingService = ratingService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initData(){

//        User user1 = this.userService.register("Sreten","Strezovski",
//                "075491218",
//                "strezovskisreten@gmail.com",
//                "https://media-exp1.licdn.com/dms/image/C4E03AQH_6ty6fU7N-Q/profile-displayphoto-shrink_200_200/0/1618226853689?e=1655337600&v=beta&t=EyCkZQ_2cvwku5-eIxC0j9Q1AYnaXvQwOiy4ri3W2c8",
//                "sreten",
//                "sreten",
//                "Macedonia", Role.ROLE_ADMIN);
//        User user2 = this.userService.register("Ivana","Taleska",
//                "075491218",
//                "ivanataleska@gmail.com",
//                "https://media-exp1.licdn.com/dms/image/C4E22AQHFQ7Z1rAafnw/feedshare-shrink_800/0/1650277240013?e=2147483647&v=beta&t=TXhPr6yoq9IoR-7TWv_V1XItQ_KTeHmOcHZQ2-ESAxE",
//                "ivana",
//                "ivana",
//                "Macedonia", Role.ROLE_MANAGER);
//        User user3 = this.userService.register("Ariton","Verus",
//                "075491218",
//                "aritonverus@gmail.com",
//                "https://images.unsplash.com/photo-1622151834625-66296f9f0e96?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8bWFuJTIwd29ya2luZ3xlbnwwfHwwfHw%3D&w=1000&q=80",
//                "ariton",
//                "ariton",
//                "Macedonia", Role.ROLE_USER);
      ///NIGHCLUBS
        Venue venue1 = this.venueService.create("EPICENTAR",150,"https://mkc.mk/wp-content/uploads/2021/02/%D0%BC%D0%BA%D1%86-%D0%BF%D0%B0%D1%80%D0%BA.jpg",EventType.NIGHTCLUB);
        Venue venue2 = this.venueService.create("HAVANA",200,"https://mkc.mk/wp-content/uploads/2021/02/%D0%BC%D0%BA%D1%86-%D0%BF%D0%B0%D1%80%D0%BA_1.jpg",EventType.NIGHTCLUB);
        Venue venue3 = this.venueService.create("CUBALIBRE",100,"https://mkc.mk/wp-content/uploads/2020/02/%D0%BA%D0%B8%D0%BD%D0%BE-%D0%A4%D1%80%D0%BE%D1%81%D0%B8%D0%BD%D0%B0-8-1.jpg",EventType.BAR);
        Venue venue4 = this.venueService.create("INCLUSIVE",500,"https://mkc.mk/wp-content/uploads/2020/02/%D0%9F%D0%BB%D0%B0%D1%82%D0%BE_2.jpg",EventType.NIGHTCLUB);
      ///BAR
        Venue venue5 = this.venueService.create("IRISH PUB DUBLIN",100,"https://menus.mk/wp-content/uploads/2019/06/dublin.jpg",EventType.BAR);
        Venue venue6 = this.venueService.create("TINO'S PUB",120,"http://www.hoteltino.com.mk/images/2020/11/19/125307762_995272104304896_3412034761566216080_o.jpg",EventType.BAR);
        //PARTY
        Venue venue7 = this.venueService.create("STADION BILJANINI",20000,"https://sportmedia.mk/wp-content/uploads/2021/09/biljanini-izvori-1.jpg",EventType.PARTY);
        //    LIVE_MUSIC,
        Venue venue8 = this.venueService.create("GARSON",230,"https://dynamic-media-cdn.tripadvisor.com/media/photo-o/09/a5/51/06/van-gogh-bar.jpg?w=300&h=300&s=1",EventType.LIVE_MUSIC);
        //    CONCERT
        Venue venue9 = this.venueService.create("MAKEDONSKA FILHARMONIJA",600,"https://mktickets.mk/wp-content/uploads/2018/09/venue-filharmonija.jpg",EventType.CONCERT);
        //    THEATRE
        Venue venue10 = this.venueService.create("ANTICKI TEATAR OHRID",700,"https://www.vikexperience.com/wp-content/uploads/elementor/thumbs/ohrid-anticki-teatar-pkyi6zp03iishcoaqfhoxh7cv15hcu4ka2szie9k4g.jpg",EventType.THEATRE);
        //    CINEMA,
        Venue venue11 = this.venueService.create("CINEPLEX",2000,"https://visitskopje.mk/wp-content/uploads/2018/12/cineplexx_1477995780-804336.jpg",EventType.CINEMA);


        Event event1 = this.eventService.create("CUBALKANIKA","Настан каде се изучуваат традиционалните танци на Cuba исполнети со смеа и забава",
                "https://trendseteri.com/wp-content/uploads/2016/09/13532788_1252676748106247_3658128812153302835_n.jpg",
                EventType.NIGHTCLUB,
                LocalDate.of(2022,8,15),
                venue3.getId(),200,300);
        Event event2 = this.eventService.create("THURSDAY SHAKE","Уживајте во најдобрата хип хоп музика со Dj Irie Scratch",
                "https://popup.mk/wp-content/uploads/2019/03/TB10532.jpg",
                EventType.NIGHTCLUB,
                LocalDate.of(2022,7,18),
                venue1.getId(),300,600);
        Event event3 = this.eventService.create("DJ BLAIR LEA","Вечер исполнета со хип хоп/српска музика ",
                "https://www.ohridnews.com/wp-content/uploads/2022/04/Disko-Inkluziv-Ohrid4.jpg",
                EventType.NIGHTCLUB,
                LocalDate.of(2022,5,30),
                venue4.getId(),400,100);
        Event event4 = this.eventService.create("DRINKING GAMES","Најдобри игри со алкохол исполнети со многу дружба",
                "https://explore.mk/wp-content/uploads/2020/02/10-2.jpg",
                EventType.BAR,
                LocalDate.of(2022,4,22),
                venue5.getId(),800,10);
        Event event5 = this.eventService.create("OHRID CALLING","Фестивал со најпознати диџеи од светот",
                "https://vistina.com.mk/wp-content/uploads/2020/07/Screenshot_41-1-960x556.jpg",
                EventType.PARTY,
                LocalDate.of(2022,9,15),
                venue7.getId(),3000,12000);
        Event event6 = this.eventService.create("THE HUNTS","Музика во живо со најдобриот охридски бенд",
                "https://songsspace.com/wp-content/uploads/2021/09/GH-Band-Playing-jpg-1559750190.jpg",
                EventType.LIVE_MUSIC,
                LocalDate.of(2022,11,12),
                venue8.getId(),200,200);
        Event event7 = this.eventService.create("BALET OSTERIKS","Прекрасна вечер со убавина на балетскиот тим",
                "https://operabalet.mk/wp-content/uploads/2018/01/zizel-slajd.jpg",
                EventType.CONCERT,
                LocalDate.of(2022,10,6),
                venue9.getId(),100,250);
        Event event8 = this.eventService.create("ЗЛАТНА БУБАМАРА","Хумористично шоу",
                "https://cdn.radiobubamara.mk/wp-content/uploads/2020/02/%D0%B7%D0%BB%D0%B0%D1%82%D0%BD%D0%B0-23-1.jpg",
                EventType.THEATRE,
                LocalDate.of(2022,9,5),
                venue10.getId(),1500,50);
        Event event9 = this.eventService.create("FOCUS","Акција",
                "https://images.metadata.sky.com/pd-image/1b80324e-1265-442d-9729-b688db079172/16-9",
                EventType.CINEMA,
                LocalDate.of(2022,7,19),
                venue11.getId(),400,20);

//        Rating rating1 = this.ratingService.create("Very pleasent expirience!",user1.getId(),event1.getId());
//        Rating rating2 = this.ratingService.create("Wonderful!",user2.getId(),event1.getId());
    }
}
