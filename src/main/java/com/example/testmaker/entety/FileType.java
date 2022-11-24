package com.example.testmaker.entety;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import java.io.File;

@Data
@AllArgsConstructor
public class FileType {
    File file;
    Type type;
}
