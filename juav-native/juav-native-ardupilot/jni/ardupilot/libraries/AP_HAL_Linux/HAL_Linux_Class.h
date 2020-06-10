#pragma once

#include <AP_HAL/AP_HAL.h>

class HAL_Linux : public AP_HAL::HAL {
public:
    HAL_Linux();
    void run(int argc, char* const* argv, Callbacks* callbacks) const override;

    void setup_signal_handlers() const;

    static void exit_signal_handler(int);

    bool juavHalLinuxShouldExit() {return _should_exit;}
    void juavHalLinuxInitializationPriorToControlLoop(int argc, char * argv[], Callbacks* callbacks);
    void juavHalLinuxAfterShouldExit();

protected:
    bool _should_exit = false;
};
