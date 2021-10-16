import configJson from './auth0-config.json';

export function getAuth0Config() {
  const audience =
    configJson.audience && configJson.audience !== 'YOUR_API_IDENTIFIER'
      ? configJson.audience
      : null;

  return {
    domain: configJson.domain,
    clientId: configJson.clientId,
    ...(audience ? { audience } : null),
  };
}
