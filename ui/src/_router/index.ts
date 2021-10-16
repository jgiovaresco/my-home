import { homeRoute } from '../home';
import { booksRoute } from '../library';
import type { RouteDefinition } from './route-definition';

export const routes: RouteDefinition[] = [homeRoute, booksRoute];
