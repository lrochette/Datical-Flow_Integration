
procedure 'deploy',
  description: 'A frontend for the DaticalDB deploy action.',
{
  formalParameter 'daticalStep',
    description: 'The name of the Deployment Step in DaticalDB',
    required: '1',
    type: 'entry'
  formalParameter 'daticalResource',
      description: 'The name of the Resource where DaticalDB runs',
      required: '1',
      type: 'entry'

    step 'deploy',
      command:new File(pluginDir + "/dsl/procedures/deploy/steps/deploy.pl").text,
      shell: 'ec-perl',
      resourceName: '$[daticalResource]'
}
