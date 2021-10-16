import type { FC } from 'react';

export type RouteDefinition = {
  path: string;
  label: string;
  component: FC;
  secured?: boolean;
};
