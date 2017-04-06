package com.knowledgePodium.slingshot.commands

import com.knowledgePodium.slingshot.model.Command
import com.knowledgePodium.slingshot.spring.SpringContextManager
import groovy.util.logging.Log4j

@Log4j
class CommandExecutor {

    def execute(Command command, CommandData commandData) {
        def commandToExecute = SpringContextManager.getBean(command?.action)
        log.info "Executing command :: ${commandToExecute}"
        commandData.put("parameter", command.parameter)
        commandToExecute.execute(commandData)
    }
}
