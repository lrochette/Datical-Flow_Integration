
procedure 'deploy',
  description: 'A frontend for the DaticalDB deploy action.',
{
  formalParameter 'daticalStep',
    description: 'The name of the Deployment Step in DaticalDB',
    required: '1',
    type: 'entry'

    step 'deploy',
      subprocedure: 'Deploy',
      subproject: '/plugins/EC-Datical/project',
      actualParameter:[
        'daticalDeploymentStep': '$[daticalStep]',
        'daticalInstallPath': 'C:\\Users\\TestUser1\\DaticalDB\\repl',
        'daticalPluginsPath': 'C:\\Users\\TestUser1\\DaticalDB\\plugins',
        'daticalProjectPath': 'C:\\Users\\TestUser1\\git\\ECloudIntegration\\ECloudIntegration',
        'resource': 'datical'
      ]
}
