package ru.quest.model.level;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class LevelTypeSubSetValidator implements ConstraintValidator<LevelTypeSubset, Level> {
    private Level[] levels;

    @Override
    public void initialize(LevelTypeSubset levelTypeSubset) {
        this.levels = levelTypeSubset.anyOf();
    }

    @Override
    public boolean isValid(Level level, ConstraintValidatorContext context) {
        return level == null || Arrays.asList(levels).contains(level);
    }
}
