import type { GetIdTokenClaimsOptions, IdToken } from '@auth0/auth0-react';

type Auth0TokenAccessor = (
  options?: GetIdTokenClaimsOptions | undefined,
) => Promise<IdToken>;

let resolveFn: (
  value: Auth0TokenAccessor | PromiseLike<Auth0TokenAccessor>,
) => void;

const promise: Promise<Auth0TokenAccessor> = new Promise((resolve) => {
  resolveFn = resolve;
});

export const setTokenAccessor = (accessor: Auth0TokenAccessor) => {
  resolveFn(accessor);
};

export const getToken = async (): Promise<string> => {
  const getToken = await promise;
  return getToken().then((idToken: IdToken) => idToken.__raw);
};
