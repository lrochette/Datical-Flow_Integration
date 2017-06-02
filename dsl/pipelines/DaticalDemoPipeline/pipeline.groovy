
pipeline 'DaticalDemoPipeline',
  description: 'The main pipeline for the model',
{
  formalParameter 'OS',
    description: 'The name of the Resource where DaticalDB runs',
    required: '1',
    type: 'select',
    defaultValue: 'Windows'

  ["DEV", "QA", "SIT", "PROD"].each { _daticalStepName ->
    stage _daticalStepName, {

      task 'Forecast',
        subprocedure: 'forecast',
        subproject:pluginName,
        taskType: 'PROCEDURE',
          actualParameter: [
            'daticalStep': '$[/myStage/stageName]',
            'daticalResource': 'datical$[OS]'
          ]

      task 'Deploy',
        subprocedure: 'deploy',
        subproject:pluginName,
        taskType: 'PROCEDURE',
          actualParameter: [
            'daticalStep': '$[/myStage/stageName]',
            'daticalResource': 'datical$[OS]'
          ]

      task 'Approval',
        gateType:'POST',
        taskType: 'APPROVAL',
        notificationTemplate: 'ec_default_pipeline_notification_template',
        approver: ['admin','lrochette']
      } // End of stage
  }     // End of stage loop

  property 'ec_customEditorData', {
    property 'parameters', {
      property 'OS', {
        property 'options', {
          property 'option1', {
            property 'text', value: 'Windows'
            property 'value', value: 'Windows'
          }
          property 'option2', {
            property 'text', value: 'Linux'
            property 'value', value: 'Linux'
          }
          property 'optionCount', value: '8'
          property 'type', value: 'list'
        }
        property 'formType', value: 'standard'
      }
    }
  }

}       // pipeline
