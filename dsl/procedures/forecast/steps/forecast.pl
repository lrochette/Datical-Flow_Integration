#############################################################################
#
# Copyright 2016 Datical Inc.
#
#############################################################################
use strict;
use English;
use ElectricCommander;
$| = 1;

my $DEBUG=0;

# Create a single instance of the Perl access to ElectricCommander
my $ec = new ElectricCommander();

# Check for the OS Type
my $osIsWindows = $^O =~ /MSWin/;

my $daticalProjectPath='C:\\Users\\TestUser1\\datical\\ECloudIntegrationDemo';
if (!$osIsWindows) {
  $daticalProjectPath='/home/lrochette/datical/ECloudIntegrationDemo';
}
$ec->createJobStep({
    subproject   => "/plugins/EC-Datical/project",
    subprocedure => 'Forecast',
    resourceName => '$[daticalResource]',
    actualParameter=>[
      {actualParameterName =>'daticalDeploymentStep', value => '$[daticalStep]'},
      {actualParameterName =>'daticalInstallPath', value => '$[/myResource/datical/installPath]'},
      {actualParameterName =>'daticalPluginsPath', value => '$[/myResource/datical/pluginsPath]'},
      {actualParameterName =>'daticalProjectPath', value =>$daticalProjectPath},
      {actualParameterName =>'resource', value=>'$[daticalResource]'},
    ]
});
