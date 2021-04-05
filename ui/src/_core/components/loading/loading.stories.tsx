import type { Meta, Story } from '@storybook/react';
import { Loading, LoadingProps } from './loading';

export default {
  title: 'Components/Loading',
  component: Loading,
} as Meta;

const Template: Story<LoadingProps> = () => <Loading />;

export const Default = Template.bind({});
Default.args = {};
