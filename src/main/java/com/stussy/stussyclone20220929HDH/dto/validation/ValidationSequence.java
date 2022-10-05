package com.stussy.stussyclone20220929HDH.dto.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ValidationGroups.NotBlankGroup.class, ValidationGroups.PatternCheckGroup.class})  //왼쪽부터 우선순위가 높음
public interface ValidationSequence {
}
