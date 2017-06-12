application 'Torch Retail', {

  applicationTier 'Database', {

    component 'daticalPackage',
      pluginKey: 'EC-Artifact',
      pluginName: '$[/plugins/EC-Artifact/project/projectName]',
      {
        process 'forecast',
          description: 'Check rules to be sure no error will happen in Deploy',
          processType: 'OTHER',
          {
            processStep 'retrieve',
              errorHandling:'abortJob',
              processStepType: 'component',
              subprocedure: 'Retrieve',
              subproject: '/plugins/EC-Artifact/project',
              applicationName: null,
              applicationTierName: null,
              actualParameter: [
                'artifactName': '$[/myComponent/ec_content_details/artifactName]',
                'artifactVersionLocationProperty': '$[/myComponent/ec_content_details/artifactVersionLocationProperty]',
                'filterList': '$[/myComponent/ec_content_details/filterList]',
                'overwrite': '$[/myComponent/ec_content_details/overwrite]',
                'retrieveToDirectory': '$[/myComponent/ec_content_details/retrieveToDirectory]',
                'versionRange': '$[/myJob/ec_daticalPackage-version]'
              ]


            processStep 'forecast',
              description: 'run the forecast operation',
              errorHandling: 'failProcedure',
              processStepType: 'plugin',
              subprocedure: 'Forecast',
              subproject: '/plugins/EC-Datical/project',
              applicationName: null,
              applicationTierName: null,
              actualParameter: [
                'daticalDeploymentStep': '$[/myStage/stageName]',
                'daticalInstallPath': '$[/myProject/Datical/installationDir]\\repl',
                'daticalPluginsPath': '$[/myProject/Datical/installationDir]\\plugins',
                'daticalProjectPath': '$[/myProject/Datical/daticalProjectName]',
                'resource': '$[/myProject/Datical/resource]'
              ]

            processDependency 'retrieve',
              targetProcessStepName: 'forecast',
              branchType: 'ALWAYS'
          }

          process 'deploy',
            description: 'Deploy the DB changeset',
            processType: 'OTHER',
            {
              processStep 'retrieve',
                errorHandling:'abortJob',
                processStepType: 'component',
                subprocedure: 'Retrieve',
                subproject: '/plugins/EC-Artifact/project',
                applicationName: null,
                applicationTierName: null,
                actualParameter: [
                  'artifactName': '$[/myComponent/ec_content_details/artifactName]',
                  'artifactVersionLocationProperty': '$[/myComponent/ec_content_details/artifactVersionLocationProperty]',
                  'filterList': '$[/myComponent/ec_content_details/filterList]',
                  'overwrite': '$[/myComponent/ec_content_details/overwrite]',
                  'retrieveToDirectory': '$[/myComponent/ec_content_details/retrieveToDirectory]',
                  'versionRange': '$[/myJob/ec_daticalPackage-version]'
                ]


              processStep 'deploy',
                description: 'run the deploy operation',
                errorHandling: 'failProcedure',
                processStepType: 'plugin',
                subprocedure: 'Deploy',
                subproject: '/plugins/EC-Datical/project',
                applicationName: null,
                applicationTierName: null,
                actualParameter: [
                  'daticalDeploymentStep': '$[/myStage/stageName]',
                  'daticalInstallPath': '$[/myProject/Datical/installationDir]\\repl',
                  'daticalPluginsPath': '$[/myProject/Datical/installationDir]\\plugins',
                  'daticalProjectPath': '$[/myProject/Datical/daticalProjectName]',
                  'resource': '$[/myProject/Datical/resource]'
                ]

              processDependency 'retrieve',
                targetProcessStepName: 'deploy',
                branchType: 'ALWAYS'
              }

      }     // Component

      // Custom properties
      property 'ec_content_details', {
        property 'artifactName',
          value: 'datical:ecloudIntegrationDemo',
          expandable: '1'

        property 'artifactVersionLocationProperty', value: '/myJob/retrievedArtifactVersions/$[assignedResourceName]'
        property 'filterList', value: ''
        property 'overwrite', value: 'update'
        property 'pluginProcedure', value: 'Retrieve'
        property 'pluginProjectName', value: 'EC-Artifact', expandable: '1'
        property 'retrieveToDirectory', value: ''
        property 'versionRange', value: '', expandable:'1'

      }     // custome properties
  }         // applicationTier 'Database'
}           // aplication
