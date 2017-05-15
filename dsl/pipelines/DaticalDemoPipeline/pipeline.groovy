
pipeline 'DaticalDemoPipeline',
  description: 'The main pipeline for the mode',
{

  ["REF", "QA", "SIT", "PROD"].each { _daticalStepName ->
    stage _daticalStepName, {

      task 'Forecast',
        subprocedure: 'forecast',
        subproject:pluginName,
        taskType: 'PROCEDURE',
          actualParameter: [
            'daticalStep': '$[/myStage/stageName]',
          ]

      task 'Deploy',
        subprocedure: 'deploy',
        subproject:pluginName,
        taskType: 'PROCEDURE',
          actualParameter: [
            'daticalStep': '$[/myStage/stageName]',
          ]

      } // End of stage
  }     // End of stage loop

}       // pipeline
