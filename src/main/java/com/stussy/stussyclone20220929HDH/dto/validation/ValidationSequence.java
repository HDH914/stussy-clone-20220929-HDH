package com.stussy.stussyclone20220929HDH.dto.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({ ValidationGroups.NotBlankGroup.class,
        ValidationGroups.SizeCheckGroup.class,
        ValidationGroups.PatternCheckGroup.class,
        Default.class})  //왼쪽부터 우선순위가 높음
public interface ValidationSequence {
}
