package com.example.momocinema.data

import com.example.momocinema.listFilm
import com.example.momocinema.model.Cast
import com.example.momocinema.model.Cinema
import com.example.momocinema.model.CinemaLayout
import com.example.momocinema.model.CinemaRoom
import com.example.momocinema.model.Comment
import com.example.momocinema.model.Film
import com.example.momocinema.model.Perform
import com.example.momocinema.model.Ranking
import com.example.momocinema.model.SeatPrice
import com.example.momocinema.model.User
import java.sql.Timestamp

class Datasource() {

    fun loadCast(): List<Cast> {
        return listOf<Cast>(
            Cast(pictureUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/%ED%8B%B0%EB%AA%A8%EC%8B%9C_%EC%83%AC%EB%9D%BC%EB%A9%94_%28Timothee_Chalamet%29_%27%EB%8D%94_%ED%82%B9_%ED%97%A8%EB%A6%AC_5%EC%84%B8%27_04.png/800px-%ED%8B%B0%EB%AA%A8%EC%8B%9C_%EC%83%AC%EB%9D%BC%EB%A9%94_%28Timothee_Chalamet%29_%27%EB%8D%94_%ED%82%B9_%ED%97%A8%EB%A6%AC_5%EC%84%B8%27_04.png", name = "Timothée Chalamet", characterName = "Paul Atreides"),
            Cast(pictureUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/Zendaya_-_2019_by_Glenn_Francis.jpg/800px-Zendaya_-_2019_by_Glenn_Francis.jpg", name = "Zendaya", characterName = "Chani"),
            Cast(pictureUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b3/Rebecca_Ferguson_in_2018.jpg", name = "Rebecca Ferguson", characterName = "Lady Jessica Atreides"),
            Cast(pictureUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Cillian_Murphy_at_Berlinale_2024%2C_Ausschnitt.jpg/800px-Cillian_Murphy_at_Berlinale_2024%2C_Ausschnitt.jpg", name = "Cillian Murphy", characterName = "J. Robert Oppenheimer"),
            Cast(pictureUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Robert_Downey_Jr_2014_Comic_Con_%28cropped%29.jpg/800px-Robert_Downey_Jr_2014_Comic_Con_%28cropped%29.jpg", name = "Robert Downey Jr.", characterName = "Lewis Strauss")
        )
    }
    fun loadFilms(): List<Film> {
        return listOf<Film>(
            Film(title = "Dune: Hành tinh cát - Phần Hai Hành Tinh Cát Phần hai Hành thinh", pictureUrl = "https://cinema.momocdn.net/img/12744539559079075-2wDBg6JcjhoWyw3LCy2k4XMHOBV.jpg", releaseDate = Timestamp.valueOf("2024-03-15 00:00:00.0"), restrictAge = 16, tag = "Khoa học viễn tưởng, Phiêu lưu, Chính kịch, Hành động, Hài", ranking = Ranking(9.3f, 1800, 56, 21, 45, 141, 1507),  duration = 124, language = "Phụ đề Lồng Tiếng", description = "Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", listComment = loadComments(), cast = loadCast()),
            Film(title = "Đào, phở và piano", pictureUrl = "https://cinema.momocdn.net/img/33735630744753025-jWYfucBwXG3ePh97bM5ReT1q65X.jpg", releaseDate = Timestamp.valueOf("2024-02-24 00:00:00.0"), restrictAge = 18, tag = "Chính kịch", ranking = Ranking(9.3f, 1800, 56, 21, 45, 141, 1707), duration = 124, language = "Phụ đề", description = "Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", listComment = loadComments(), cast = loadCast()),
            Film(title = "Kung Fu Panda 4", pictureUrl = "https://cinema.momocdn.net/img/33921126334732652-7quq3UOaaB0qNM7TMGMEqqghLck.jpg", releaseDate = Timestamp.valueOf("2024-03-08 00:00:00.0"), restrictAge = 10, tag = "Gia đình, Hài, Hành động, Phiêu lưu, Hoạt hình", ranking = Ranking(9.3f, 1800, 56, 21, 45, 141, 1707), duration = 124, language = "Lồng Tiếng", description = "Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", listComment = loadComments(), cast = loadCast()),
            Film(title = "MAI", pictureUrl = "https://cinema.momocdn.net/img/30196263872528348-thumb.jpg", releaseDate = Timestamp.valueOf("2024-12-11 00:00:00.0") , restrictAge = 13, tag = "Hài", ranking = Ranking(9.3f, 1800, 56, 21, 45, 141, 1707), duration = 89, language = "Phụ đề Lồng Tiếng", description = "Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", listComment = loadComments(), cast = loadCast()),
            Film(title = "MAI", pictureUrl = "https://cinema.momocdn.net/img/30196263872528348-thumb.jpg", releaseDate = Timestamp.valueOf("2024-03-08 00:00:00.0"), restrictAge = 18, tag = "Chính kịch, Hành động", ranking = Ranking(9.3f, 1800, 56, 21, 45, 141, 1707), duration = 123, language = "Lồng Tiếng", description = "Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", listComment = loadComments(), cast = loadCast()),
            Film(title = "MAI", pictureUrl = "https://cinema.momocdn.net/img/30196263872528348-thumb.jpg", releaseDate = Timestamp.valueOf("2024-03-08 00:00:00.0"), restrictAge = 1, tag = "Chính kịch, Hành động, Hài", ranking = Ranking(9.3f, 1800, 56, 21, 45, 141, 1707), duration = 195, language = "Phụ đề", description = "Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", listComment = loadComments(), cast = loadCast())

        )
    }

    fun loadCinemaRooms(): List<CinemaRoom> {
        return listOf<CinemaRoom>(
            CinemaRoom(type = 1, name =  "ROOM1", cinemaLayout =  CinemaLayout(11,9), cinema = loadCinemas()[0]),
            CinemaRoom(type = 1, name =  "ROOM2", cinemaLayout =  CinemaLayout(11,9), cinema = loadCinemas()[0]) ,
            CinemaRoom(type = 1, name =  "ROOM3", cinemaLayout =  CinemaLayout(11,9), cinema = this.loadCinemas()[0]) ,
            CinemaRoom(type = 1, name =  "ROOM4", cinemaLayout =  CinemaLayout(11,9), cinema = this.loadCinemas()[0]) ,
            CinemaRoom(type = 1, name =  "ROOM5", cinemaLayout =  CinemaLayout(11,9), cinema = this.loadCinemas()[0]) ,
            CinemaRoom(type = 1, name =  "ROOM6", cinemaLayout =  CinemaLayout(11,9), cinema = this.loadCinemas()[0]) ,
            CinemaRoom(type = 1, name =  "ROOM7", cinemaLayout =  CinemaLayout(11,9), cinema = this.loadCinemas()[0]) ,
            CinemaRoom(type = 1, name =  "ROOM8", cinemaLayout =  CinemaLayout(11,9), cinema = this.loadCinemas()[0]) ,
            CinemaRoom(type = 1, name =  "ROOM9", cinemaLayout =  CinemaLayout(11,9), cinema = this.loadCinemas()[0])
        )
    }

    fun loadCinemas(): List<Cinema> {
        return listOf<Cinema>(      // TODO: Cinema này sẽ có thêm List<CinemaRoom>
            Cinema(name = "CGV", variant = "TP.HCM", logoUrl = "https://homepage.momocdn.net/placebrand/s/momo-upload-api-190709165424-636982880641515855.jpg"),
            Cinema(name = "Lotte", variant = "Bình Dương", logoUrl = "https://homepage.momocdn.net/blogscontents/momo-upload-api-210604170617-637584231772974269.png"),
            Cinema(name = "Galaxy", variant = "Long An", logoUrl = "https://homepage.momocdn.net/cinema/momo-upload-api-211123095138-637732578984425272.png"),
            Cinema(name = "BHD", variant = "Vũng Tàu", logoUrl = "https://homepage.momocdn.net/blogscontents/momo-upload-api-210604170453-637584230934981809.png"),
            Cinema(name = "Cinestar", variant = "Đồng Nai", logoUrl = "https://homepage.momocdn.net/blogscontents/momo-upload-api-210604170530-637584231309495829.png"),
            Cinema(name = "MegaGS", variant = "Bình Phước", logoUrl = "https://homepage.momocdn.net/blogscontents/momo-upload-api-210604170511-637584231119272266.png"),
            Cinema(name = "DCine", variant = "Hà Nội", logoUrl = "https://img.mservice.io/momo_app_v2/new_version/img/THAO.MAI/DcineLogo.png"),
            Cinema(name = "Beta", variant = "Hải Phòng", logoUrl = "https://homepage.momocdn.net/cinema/momo-upload-api-210813104719-637644484394328824.png"),
            Cinema(name = "Cinemax", variant = "Đà Nẵng", logoUrl = "https://homepage.momocdn.net/cinema/momo-upload-api-221108100132-638034984925107129.png")
        )
    }

    fun loadSeats(): List<SeatPrice> {
        return (1..13).flatMap { x -> (1..9).map { y -> SeatPrice(x = x, y = y, type = if (y > 3) "VIP" else "DEFAULT", price = if (y>3) 120000 else 100000) }}
    }


    fun loadPerforms(): List<Perform> {
        return listOf<Perform>(
            Perform(loadSeats(), film =  listFilm[0], startTime = Timestamp.valueOf("2024-04-07 09:00:00.0"), cinemaRoom = loadCinemaRooms()[0]),
            Perform(loadSeats(), listFilm[0], startTime = Timestamp.valueOf("2024-04-08 09:30:00.0"), cinemaRoom = loadCinemaRooms()[1]),
            Perform(loadSeats(),listFilm[0], startTime = Timestamp.valueOf("2024-04-08 11:30:00.0"), cinemaRoom = loadCinemaRooms()[2]),
            Perform(loadSeats(),listFilm[0], startTime = Timestamp.valueOf("2024-04-08 23:00:00.0"), cinemaRoom = loadCinemaRooms()[3]),
            Perform(loadSeats(),listFilm[0], startTime = Timestamp.valueOf("2024-04-08 19:30:00.0"), cinemaRoom = loadCinemaRooms()[4]),
            Perform(loadSeats(),listFilm[0], startTime = Timestamp.valueOf("2024-04-08 17:00:00.0"), cinemaRoom = loadCinemaRooms()[5]),
            Perform(loadSeats(),listFilm[0], startTime = Timestamp.valueOf("2024-04-08 17:30:00.0"), cinemaRoom = loadCinemaRooms()[6]),
        )
    }


    val tempUser = User(name = "Nguyễn Văn A", username = "nguyenvana")
    fun loadComments(): List<Comment> {
        return listOf<Comment>(
            Comment(user = tempUser, body = "Phim thấy cũng được.\n" +
                    "Có vài đoạn hài, vui vẻ\n" +
                    "Xem giải trí, thư giãn\n" +
                    "K hay như các phần trước\n" +
                    "Tiết tấu nhẹ nhàng\n" +
                    "Đoạn kết hơi nhanh",
                ranking = 8),
            Comment(user = tempUser, body = "Rạp khá vắng, nghe mn đánh giá phim ko hay, mạch phim nhanh. Công tâm mà nói thì hơi nhanh thiệt, nhất là lúc đánh bại Tắc kè hoa (nv hùng mạnh nhất vì hút linh hồn 7749 vị ở Cõi linh hồn) và nội tâm nv Cáo Trân thay đổi lẹ ghê. Coi giải trí ok", ranking = 9),
            Comment(user = tempUser, body = "Phải nói sao nhỉ. Về cốt truyện, đây là một cách dẫn dắt ổn. Cốt truyện đơn giản, dễ hiểu. Phù hợp với đoạn kết của phần 3. Các tình tiết trong câu chuyện hợp lý. Những cú plot tuy không quá bất ngờ nhưng ổn áp. Những pha tấu hài hợp lý, hay. Tuy nhiên so với các phần phim trước, nó thụt lùi. \n" +
                    "Phản diện: so với 3 phần trước, phản diện của phần này được xây dựng rất mạnh cả về năng lực lẫn quyền lực, và đáng lẽ với cái profile ấy, chúng ta nên có một phản diện tàn ác, mưu mẹo và đáng sợ hơn. Tuy nhiên, bản thân mình không có cảm giác áp lực đến tuyệt vọng như những phần phim trước, phản diện không thật sự mạnh nhưng tự tin lại có thừa. Có thể mình hơi khó tính nhưng nếu bản thân mình là phản diện, mình còn có nhiều trò hay ho hơn để làm khổ Po.\n" +
                    "Chính diện: ở phần trước, Po đã luyện được đến cảnh giới \"tiên hiệp\", sử dụng thuần thục khí, và đánh bại Kai với sức mạnh áp đảo tuyệt đối. Nhưng sức mạnh ấy không còn rõ ràng ở phần này nữa. Đến độ mình nghĩ rằng cậu ấy phải mất hơn 70% sức mạnh so với phần trước vậy. \n" +
                    "Zhen được xây dựng ổn. Nhưng chìa khóa chiến thắng phản diện không được mình đánh giá cao. Nếu không muốn nói là có phần gượng gạo.", ranking = 6),
            Comment(user = tempUser, body = "Tác phẩm hay, có nội dung, tuy chưa gọi là lôi cuốn nhưng đối với trẻ em và mang tính giải trí, màn võ công mãn nhãn, từng tiết câu chuyện có liên tục, nhưng sự liên kết câu chuyện chưa như phần 1 và 2", 10),
            Comment(user = tempUser, body = "Mình đặt vé sớm đi xem phim, thấy hot nhiều người đặt nên hy vọng phim lần này sẽ bom tấn lắm nhưng đi xem về bị thất vọng. Phản diện buff bá vl nhưng bị main và 1 đứa trình ngang tay quất phát ngủm luôn, các joke của phim cũng nhạt.", ranking = 4),
            Comment(user = tempUser, body = "dksajdlkjaslj", 1),
            Comment(user = tempUser, body = "djslkajskdjlsajljMình đặt vé sớm đi xem phim, thấy hot nhiều người đặt nên hy vọng phim lần này sẽ bom tấn lắm nhưng đi xem về bị thất vọng. Phản diện buff bá vl nhưng bị main và 1 đứa trình ngang tay quất phát ngủm luôn, các joke của phim cũng nhạt.Mình đặt vé sớm đi xem phim, thấy hot nhiều người đặt nên hy vọng phim lần này sẽ bom tấn lắm nhưng đi xem về bị thất vọng. Phản diện buff bá vl nhưng bị main và 1 đứa trình ngang tay quất phát ngủm luôn, các joke của phim cũng nhạt.", ranking = 3)
        )

    }
}