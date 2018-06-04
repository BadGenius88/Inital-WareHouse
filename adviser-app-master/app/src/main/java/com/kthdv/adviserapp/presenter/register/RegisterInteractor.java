package com.kthdv.adviserapp.presenter.register;


import com.kthdv.adviserapp.models.AdviserDto;
import com.kthdv.adviserapp.presenter.BaseInteractor;

/**
 * Created by TranThanhTung on 24/01/2018.
 */

public interface RegisterInteractor extends BaseInteractor {
    void register(AdviserDto adviserDto,
                  OnRegisterCompleteListener listener);
}
