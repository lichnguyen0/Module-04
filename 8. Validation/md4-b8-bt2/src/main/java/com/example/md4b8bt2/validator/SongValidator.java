package com.example.md4b8bt2.validator;


import com.example.md4b8bt2.model.Song;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SongValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Song.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Song song = (Song) target;

        // Kiểm tra ký tự đặc biệt cho title và artist
        String specialChars = "@;,.=+";

        if(song.getTitle() != null && song.getTitle().matches(".*[" + specialChars + "].*")) {
            errors.rejectValue("title", "title.specialChar", "Tên bài hát không được chứa ký tự đặc biệt");
        }

        if(song.getArtist() != null && song.getArtist().matches(".*[" + specialChars + "].*")) {
            errors.rejectValue("artist", "artist.specialChar", "Tên nghệ sĩ không được chứa ký tự đặc biệt");
        }

        // Chỉ cấm các ký tự đặc biệt nhất định, cho phép chữ, số, khoảng trắng, -, /, &, dấu phẩy, tiếng Việt
        String genreSpecial = "@;=+!";
        if (song.getGenre() != null && song.getGenre().matches(".*[" + genreSpecial + "].*")) {
            errors.rejectValue("genre", "genre.specialChar",
                    "Thể loại nhạc chỉ được phép chứa chữ, số, dấu cách, dấu -, /, &, dấu phẩy và chữ tiếng Việt");
        }

    }
}
