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
import com.example.momocinema.repository.CINEMA
import com.example.momocinema.repository.CINEMA_ROOM
import com.example.momocinema.repository.COMMENT
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.FILM_CAST
import com.example.momocinema.repository.PERFORM
import com.example.momocinema.repository.RANKING
import com.example.momocinema.repository.TAG
import com.example.momocinema.repository.USER
import java.sql.Timestamp

class DatasourceCloneAPIData() {

    fun loadCast(): List<FILM_CAST> {
        return listOf<FILM_CAST>(
            FILM_CAST(1, "Paul Atreides", "An interesting cast"),
            FILM_CAST(2, "Paul Atreides", "An interesting cast"),
            FILM_CAST(3, "Paul Atreides", "An interesting cast"),
            FILM_CAST(4, "Paul Atreides", "An interesting cast"),
            FILM_CAST(5, "Paul Atreides", "An interesting cast"),
        )
    }

    fun loadUser(): List<USER> {
        return listOf<USER>(
            USER(1, "Alex Gi1", "alexgi", 1),
            USER(2, "Alex Gi2", "alexgi", 1),
            USER(3, "Alex Gi3", "alexgi", 1),
            USER(4, "Alex Gi4", "alexgi", 1),
            USER(5, "Alex Gi5", "alexgi", 1),
            USER(6, "Alex Gi6", "alexgi", 1),
            USER(7, "Alex Gi7", "alexgi", 1),
        )
    }
    fun loadRanking(): List<RANKING> {
        return listOf<RANKING>(
            RANKING(1, 1, 8, 8, ""),
            RANKING(8, 1, 9, 9, ""),
            RANKING(2, 5, 5, 7, ""),
            RANKING(3, 3, 7, 5, ""),
            RANKING(4, 4, 9, 6, ""),
            RANKING(5, 5, 10, 2, ""),
            RANKING(6, 1, 1, 4, ""),
            RANKING(7, 2, 1, 8, ""),
        )
    }
    fun loadFilms(): List<FILM> {
        return listOf<FILM>(
            FILM(1, "Dune: Hành tinh cát - Phần Hai Hành Tinh Cát Phần hai Hành thinh","Chau Khai Phong", "Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", "https://cinema.momocdn.net/img/12744539559079075-2wDBg6JcjhoWyw3LCy2k4XMHOBV.jpg", "riVRelNG8YM", Timestamp.valueOf("2024-03-15 00:00:00.0"), "Phụ đề Lồng Tiếng", 16, 124),
            FILM(2, "Damn: Hành tinh cát - Phần Hai Hành Tinh Cát Phần hai Hành thinh","Chau ", "2Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", "https://cinema.momocdn.net/img/12744539559079075-2wDBg6JcjhoWyw3LCy2k4XMHOBV.jpg", "riVRelNG8YM", Timestamp.valueOf("2024-03-15 00:00:00.0"), "Phụ đề Lồng Tiếng", 16, 124),
            FILM(3, "Du: Hành tinh cát - Phần Hai Hành Tinh Cát Phần hai Hành thinh","Chau Khai ", "3Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", "https://cinema.momocdn.net/img/12744539559079075-2wDBg6JcjhoWyw3LCy2k4XMHOBV.jpg", "riVRelNG8YM", Timestamp.valueOf("2024-03-15 00:00:00.0"), "Phụ đề Lồng Tiếng", 16, 124),
            FILM(4, "Did: Hành tinh cát - Phần Hai Hành Tinh Cát Phần hai Hành thinh","Toi", "4Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", "https://cinema.momocdn.net/img/12744539559079075-2wDBg6JcjhoWyw3LCy2k4XMHOBV.jpg", "riVRelNG8YM", Timestamp.valueOf("2024-03-15 00:00:00.0"), "Phụ đề Lồng Tiếng", 16, 124),
            FILM(5, "Done: Hành tinh cát - Phần Hai Hành Tinh Cát Phần hai Hành thinh","You", "5Hãy theo dõi hành trình thần thoại của Paul Atreides khi anh đoàn kết với Chani và Fremen trong khi trên con đường trả thù những kẻ âm mưu phá hoại gia đình anh. Đứng trước sự lựa chọn giữa tình yêu của đời mình và số phận của vũ trụ đã biết, Paul cố gắng ngăn chặn một tương lai khủng khiếp mà chỉ có anh mới có thể nhìn thấy", "https://cinema.momocdn.net/img/12744539559079075-2wDBg6JcjhoWyw3LCy2k4XMHOBV.jpg", "riVRelNG8YM", Timestamp.valueOf("2024-03-15 00:00:00.0"), "Phụ đề Lồng Tiếng", 16, 124),
        )
    }
    fun loadTags(): List<TAG> {
        return listOf<TAG>(
            TAG(1, "Kinh dị, Tình dục"),
            TAG(2, "Kinh dị, Tình dục"),
            TAG(3, "Kinh dị, Tình dục"),
            TAG(4, "Kinh dị, Tình dục"),
            TAG(5, "Kinh dị, Tình dục"),
            TAG(6, "Kinh dị, Tình dục"),
            TAG(7, "Kinh dị, Tình dục"),
        )
    }

    fun loadCinemaRooms(): List<CINEMA_ROOM> {
        return listOf<CINEMA_ROOM>(
            CINEMA_ROOM(1, 1, 1, 1, "ROOM1"),
            CINEMA_ROOM(2, 2, 2, 2, "ROOM2"),
            CINEMA_ROOM(3, 3, 3, 3, "ROOM3"),
            CINEMA_ROOM(4, 4, 4, 4, "ROOM4"),
            CINEMA_ROOM(5, 5, 5, 5, "ROOM5"),
            CINEMA_ROOM(6, 2, 6, 6, "ROOM6"),
            CINEMA_ROOM(7, 7, 7, 7, "ROOM7"),
        )
    }

    fun loadCinemas(): List<CINEMA> {
        return listOf<CINEMA>(
            CINEMA(1, "TP.HCM", "CGV"),
            CINEMA(2, "Thái Bình", "LOTTE"),
            CINEMA(3, "Quảng Trị", "RIO"),
            CINEMA(4, "Huế", "BIGC"),
            CINEMA(5, "Hà Nội", "CGV"),
            CINEMA(6, "Thanh Hóa", "BIGC"),
            CINEMA(7, "Quảng Nam", "RIO"),
            CINEMA(8, "Đà Nẳng", "LOTTE"),
            )
    }

    fun loadSeats(): List<SeatPrice> {
        return (1..13).flatMap { x -> (1..9).map { y -> SeatPrice(x = x, y = y, type = if (y > 3) "VIP" else "DEFAULT", price = if (y>3) 120000 else 100000) }}
    }


    fun loadPerforms(): List<PERFORM> {
        return listOf<PERFORM>(
            PERFORM(1, 1, 1, 1, 1, 45000,Timestamp.valueOf("2024-04-23 09:30:00.0"), Timestamp.valueOf("2024-03-23 10:30:00.0")),
            PERFORM(2, 2, 2, 2, 2, 45000,Timestamp.valueOf("2024-04-23 09:30:00.0"), Timestamp.valueOf("2024-03-23 10:30:00.0")),
            PERFORM(3, 3, 3, 3, 3, 45000,Timestamp.valueOf("2024-04-23 09:30:00.0"), Timestamp.valueOf("2024-03-23 10:30:00.0")),
            PERFORM(4, 4, 4, 4, 4, 45000,Timestamp.valueOf("2024-03-23 09:30:00.0"), Timestamp.valueOf("2024-03-23 10:30:00.0")),
            PERFORM(5, 5, 5, 5, 5, 45000,Timestamp.valueOf("2024-03-23 09:30:00.0"), Timestamp.valueOf("2024-03-23 10:30:00.0")),
            PERFORM(6, 5, 6, 6, 6, 45000,Timestamp.valueOf("2024-03-23 09:30:00.0"), Timestamp.valueOf("2024-03-23 10:30:00.0")),
            PERFORM(7, 7, 7, 7, 7, 45000,Timestamp.valueOf("2024-03-23 09:30:00.0"), Timestamp.valueOf("2024-03-23 10:30:00.0")),

        )
    }


    val tempUser = User(name = "Nguyễn Văn A", username = "nguyenvana")
    fun loadComments(): List<COMMENT> {
        return listOf<COMMENT>(
            COMMENT(1, 1, 1, "Phim thấy cũng được.\n" +
                    "Có vài đoạn hài, vui vẻ\n" +
                    "Xem giải trí, thư giãn\n" +
                    "K hay như các phần trước\n" +
                    "Tiết tấu nhẹ nhàng\n" +
                    "Đoạn kết hơi nhanh"),
            COMMENT(1, 2, 1, "Rạp khá vắng, nghe mn đánh giá phim ko hay, mạch phim nhanh. Công tâm mà nói thì hơi nhanh thiệt, nhất là lúc đánh bại Tắc kè hoa (nv hùng mạnh nhất vì hút linh hồn 7749 vị ở Cõi linh hồn) và nội tâm nv Cáo Trân thay đổi lẹ ghê. Coi giải trí ok"),
            COMMENT(2, 2, 2, "Phải nói sao nhỉ. Về cốt truyện, đây là một cách dẫn dắt ổn. Cốt truyện đơn giản, dễ hiểu. Phù hợp với đoạn kết của phần 3. Các tình tiết trong câu chuyện hợp lý. Những cú plot tuy không quá bất ngờ nhưng ổn áp. Những pha tấu hài hợp lý, hay. Tuy nhiên so với các phần phim trước, nó thụt lùi. \n" +
                    "Phản diện: so với 3 phần trước, phản diện của phần này được xây dựng rất mạnh cả về năng lực lẫn quyền lực, và đáng lẽ với cái profile ấy, chúng ta nên có một phản diện tàn ác, mưu mẹo và đáng sợ hơn. Tuy nhiên, bản thân mình không có cảm giác áp lực đến tuyệt vọng như những phần phim trước, phản diện không thật sự mạnh nhưng tự tin lại có thừa. Có thể mình hơi khó tính nhưng nếu bản thân mình là phản diện, mình còn có nhiều trò hay ho hơn để làm khổ Po.\n" +
                    "Chính diện: ở phần trước, Po đã luyện được đến cảnh giới \"tiên hiệp\", sử dụng thuần thục khí, và đánh bại Kai với sức mạnh áp đảo tuyệt đối. Nhưng sức mạnh ấy không còn rõ ràng ở phần này nữa. Đến độ mình nghĩ rằng cậu ấy phải mất hơn 70% sức mạnh so với phần trước vậy. \n" +
                    "Zhen được xây dựng ổn. Nhưng chìa khóa chiến thắng phản diện không được mình đánh giá cao. Nếu không muốn nói là có phần gượng gạo."),
            COMMENT(3, 3, 3, "Rạp khá vắng, nghe mn đánh giá phim ko hay, mạch phim nhanh. Công tâm mà nói thì hơi nhanh thiệt, nhất là lúc đánh bại Tắc kè hoa (nv hùng mạnh nhất vì hút linh hồn 7749 vị ở Cõi linh hồn) và nội tâm nv Cáo Trân thay đổi lẹ ghê. Coi giải trí ok"),
            COMMENT(4, 4, 4, "Rạp khá vắng, nghe mn đánh giá phim ko hay, mạch phim nhanh. Công tâm mà nói thì hơi nhanh thiệt, nhất là lúc đánh bại Tắc kè hoa (nv hùng mạnh nhất vì hút linh hồn 7749 vị ở Cõi linh hồn) và nội tâm nv Cáo Trân thay đổi lẹ ghê. Coi giải trí ok"),
            COMMENT(5, 5, 5, "Rạp khá vắng, nghe mn đánh giá phim ko hay, mạch phim nhanh. Công tâm mà nói thì hơi nhanh thiệt, nhất là lúc đánh bại Tắc kè hoa (nv hùng mạnh nhất vì hút linh hồn 7749 vị ở Cõi linh hồn) và nội tâm nv Cáo Trân thay đổi lẹ ghê. Coi giải trí ok"),

            )

    }
}