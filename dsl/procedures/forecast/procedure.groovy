
procedure 'forecast',
  description: 'A frontend for the DaticalDB forecast action.',
{
  formalParameter 'daticalStep',
    description: 'The name of the Forecast Step in DaticalDB',
    required: '1',
    type: 'entry'
  formalParameter 'daticalResource',
      description: 'The name of the Resource where DaticalDB runs',
      required: '1',
      type: 'entry'

    step 'forecast',
      command:new File(pluginDir + "/dsl/procedures/forecast/steps/forecast.pl").text,
      shell: 'ec-perl',
      resourceName: '$[daticalResource]'
}
