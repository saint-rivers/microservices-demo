package com.exmaple.users.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Configuration
public class MapperConfig {
    public static String formatDateToString(Object v) {
        String dateFormat = "yyyy-MM-dd";
        if (v != null)
            return new SimpleDateFormat(dateFormat).format(v);
        else return "";
    }

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
//
//        Converter<LocalDateTime, String> formatDate = ctx -> ctx.getSource() != null
//                ? formatDateToString(ctx.getSource())
//                : "";
//        modelMapper.addConverter(formatDate);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }
}
